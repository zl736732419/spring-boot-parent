package com.zheng.springboot.configuration.yaml;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 通过配置@EnableConfigurationProperties可以将标注有@ConfigurationProperties的bean加入到spring容器中
 * 如果不配置这两个注解，那么需要手动配置@Component用来标记@ConfigurationProperties对应的bean
 * @Author zhenglian
 * @Date 2018/5/15 17:19
 */
@Configuration
@EnableConfigurationProperties({Server.class, User.class})
public class MyConfiguration {
}
