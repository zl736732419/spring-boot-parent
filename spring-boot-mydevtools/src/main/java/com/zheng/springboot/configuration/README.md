#### application.properties
##### 默认配置
application.properties配置文件通常用于重新一些默认变量值或者定义自己的环境变量
默认配置文件加载位置：
1. 当前目录/config
2. 当前目录
3. classpath/config
4. classpath

1.2点适合生产环境，比如打包成jar运行，当前目录指的是jar包目录，运行时必须先路由到jar包命令再执行java -jar demo.jar命令
3.4点适合在开发环境中使用，优先级从1->4依次递减
##### 自定义配置文件名称和目录
默认配置文件名称为:application.properties,可以通过spring.config.name修改配置文件名称
配置文件的目录默认为上面4个不同目录，可以通过spring.config.location指定目录
spring.config.name和spring.config.location需要通过命令行变量的方式来指定，比如
java -jar demo.jar --spring.config.name=demo
java -jar demo.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties
如果spring.config.location指定的是目录而不是文件，那么需要以/结尾，比如
spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/
需要注意的是spring.config.location配置的目录加载顺序是相反的，上面的目录配置，在进行查找时则会变成：
1. file:./config/
2. file:./
3. classpath:/config/
4. classpath:/

spring.config.location会覆盖掉默认配置的目录，如果只是想再添加几个目录，可以使用
spring.config.additional-location,使用它指定的目录会优先于默认的目录
比如spring.config.additional-location=file:./custom-config/,classpath:custom-config/,最后的优先级为
1. classpath:custom-config/
2. file:./custom-config/
3. file:./config/
4. file:./
5. classpath:/config/
6. classpath:/

spring.config.additional-location也应该在jar包启动的时候通过命令行指定

#### profile-specific properties
可以根据不同的运行环境匹配不同的配置文件，profile配置文件与默认配置文件一样，具有相同的配置目录
当使用profile配置文件时，它总是会替换掉非profile配置文件中的配置项，比如
classpath:config/application.properties
name=default
classpath:application-dev.properties
name=dev
最终name的取值dev
profile环境通过spring.profiles.active参数指定，java -jar demo.jar --spring.profiles.active=dev
需要注意的是如果在使用profile时指定了spring.config.location，那么要想使指定的location中的配置文件对profile生效，location
只能设置为目录的形式，而不能指定配置文件路径
也可以通过编码指定运行环境，主要是为了方便测试
```
new SpringApplicationBuilder()
    ...
    .profiles("dev")
    .run(args);
```
在配置文件中也可以通过${var_name}直接使用前面定义的变量


#### yaml配置文件
yaml配置文件目录与默认配置文件一样，默认都是在
1. 当前目录/config
2. 当前目录
3. classpath/config
4. classpath

spring boot中提供了两种加载器，用于加载yaml配置参数
YamlPropertiesFactoryBean 将yaml配置参数加载成properties
YamlMapFactoryBean 将yaml配置参数加载成map形式

配置文件名称：application.yaml
获取yaml配置文件中的配置可以采用@ConfigurationProperties的方式
比如存在下面的配置
```my.servers[0]=dev.example.com
   my.servers[1]=another.example.com
```
   
可以定义实体映射到配置上
```
@ConfigurationProperties(prefix="my")
   public class Config {
   	private List<String> servers = new ArrayList<String>();
   	public List<String> getServers() {
   		return this.servers;
   	}
}
```

如果要引用该配置对象，可以有两种方式：
1. 通过@Component将配置实体手动加入到spring容器中
2. 通过spring boot @Configuration @EnableConfigurationProperties(Config.class)自动加入到spring容器中
```
@Configuration
   @EnableConfigurationProperties(AcmeProperties.class)
   public class MyConfiguration {
}
```
通过上面配置后，配置实体可以直接在其他bean中被引用了
```
@Service
public class MyService {
    @Autowired
    private Config config;
}
```
##### 将配置作用到第三方bean中
我们可以将本地的配置作用于第三方jar包中的实体上，并将其交由spring容器管理
@ConfigurationProperties(prefix = "another")
@Bean
public AnotherComponent anotherComponent() {
	...
}

##### 宽松匹配
采用@ConfigurationProperties注解可以使用宽松的属性绑定方式，
spring boot 不严格要求配置的名称与给定的属性名完全一样，可以有几种不同的表达方式
比如属性名firstName可以与配置文件中的first_name,firstName,first-name三种形式成功匹配

##### 映射map
```
@ConfigurationProperties("acme")
public class AcmeProperties {
	private final Map<String, MyPojo> map = new HashMap<>();
	public Map<String, MyPojo> getMap() {
		return this.map;
	}
}
配置文件如下：
acme:
  map:
    key1:
      name: my name 1
      description: my description 1
---
spring:
  profiles: dev
acme:
  map:
    key1:
      name: dev name 1
    key2:
      name: dev name 2
      description: dev description 2
```
当profile=dev时，AcmeProperties.map包含两个entry:key1对应name=dev name 1,description=my description 1
的server, key2对应name=dev name 2, description=dev description 2的server

##### 对duration日期类型的转换
spring boot实现了对Duration类型的日期类型配置的转换
```
@ConfigurationProperties("app.system")
public class AppSystemProperties {
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout = Duration.ofSeconds(30);
    
    public Duration getSessionTimeout() {
        return this.sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
配置文件：
app.system.sessionTimeout=500s
```
在配置文件中设置Duration类型的参数时可以指定单位：
ns nanoseconds
ms milliseconds
s seconds
m minutes
h hours
d days
如果没有指定单位，则根据实体中指定的@DurationUnit来进行判断

##### 配置验证
spring boot内部实现了JSR-303验证框架，可以对给定的配置进行验证
```
@ConfigurationProperties(prefix="acme")
@Validated
public class AcmeProperties {
	@NotNull
	private InetAddress remoteAddress;
	// ... getters and setters
}
```
当然也可以自己实现验证框架：
具体参考：
https://github.com/spring-projects/spring-boot/tree/v2.0.2.RELEASE/spring-boot-samples/spring-boot-sample-property-validation
##### @ConfigurationProperties vs. @Value
                  ConfigurationProperties    Value
松散绑定              yes                       no
Meta-data support    yes                       no
SpEL evaluation      no                        yes