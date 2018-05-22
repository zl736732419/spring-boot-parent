package com.zheng.springboot.shutdown;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:31
 */
@Controller
public class ShutdownController extends ApplicationObjectSupport {
    /**
     * 通过实现ExitCodeGenerator，返回特定的shutdown状态码
     * @return
     */
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }
    
    @RequestMapping(value = "/shutdown")
    public String shutdown() {
        System.out.println("shutdown spring boot application:");
        System.exit(SpringApplication.exit(super.getApplicationContext(), exitCodeGenerator()));
        return "ok";
    }
    
}
