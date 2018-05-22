package com.zheng.springboot.profiles.service.impl;

import com.zheng.springboot.profiles.service.HelloService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 通过profile区分不同的类实现
 * @Author zhenglian
 * @Date 2018/5/16 9:08
 */
@Profile("development")
@Service
public class DevelopmentHelloService implements HelloService {
    @Override
    public String hi() {
        return "hi, i am development robot";
    }
}
