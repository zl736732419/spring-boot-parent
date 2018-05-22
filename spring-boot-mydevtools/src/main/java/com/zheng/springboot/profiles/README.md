profile可以实现不同环境提供不同的组件实现，具体就是在@Componment或者@Configuration标注的类上添加@Profile
激活profile:
1. 可以在application.properties中添加spring.profiles.active=development
2. 可以在启动时添加命令行参数--spring.profiles.active=development
3. 可以通过spring.profiles.include指定一次可以激发的多个环境，当然也可以通过JAVA API设置SpringApplication.setAdditionalProfiles(…​)
4. 还可以通过SpringApplicationBuilder.profiles("development")来触发某一个profile

通过prod触发proddb和prodmq
```
spring.profiles: prod
spring.profiles.include:
  - proddb
  - prodmq
```
当设置--spring.profiles.active=prod时，proddb prodmq也就被激发了
