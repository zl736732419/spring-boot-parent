package com.zheng.springboot.profiles.service.impl;

import com.zheng.springboot.profiles.service.HelloService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 2018/5/16 9:07
 */
@Profile("production")
@Service
public class ProductionHelloService implements HelloService {
    @Override
    public String hi() {
        return "hi, i am production robot";
    }
}
