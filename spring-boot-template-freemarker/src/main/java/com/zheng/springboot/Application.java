package com.zheng.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * spring boot入口
 * @SpringBootApplication 相当于 @EnableAutoConfiguration @ComponentScan @Configuration
 * @EnableAutoConfiguration springboot根据依赖的jar自动配置项目
 * @ComponentScan 这里相当于对com.zheng.springboot包及其子包进行扫描
 * @Configuration 允许注册或者导入其他bean,这些bean以(@Controller, @Component, @Service, @Repository)标记
 * @ServletComponentScan 用于自动注册@WebServlet,@WebFilter,@WebListener
 * 
 * @Author zhenglian
 * @Date 2018/5/14 11:19
 */
@SpringBootApplication
@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        runWithBuilder(args);
    }
    
    public static void quickStart(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    public static void runWithoutDevtools(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }
    
    public static void runWithBuilder(String[] args) {
        // 流式编程
        new SpringApplicationBuilder()
                .sources(Application.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .listeners((event) -> System.out.println("listener: " + event.toString()))
                // 通过编程方式指定运行环境
                .profiles("development")
                .run(args);
    }
    
}
