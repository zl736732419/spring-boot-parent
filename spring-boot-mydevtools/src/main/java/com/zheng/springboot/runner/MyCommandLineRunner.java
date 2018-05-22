package com.zheng.springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:13
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner:");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
