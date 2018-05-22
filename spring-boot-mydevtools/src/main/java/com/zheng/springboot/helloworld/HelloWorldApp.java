package com.zheng.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个springboot程序
 * 启动： mvn spring-boot:run
 * 打包成jar: 
 * 1. 添加spring-boot-maven-plugin插件
 * 2. mvn package
 * 3. 运行java -jar spring-boot-study-1.0-SNAPSHOT.jar 启动
 * @Author zhenglian
 * @Date 2018/5/14 9:57
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldApp {
    
    @GetMapping(value = "/hello")
    public String home() {
        return "hello world";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApp.class, args);
    }
}
