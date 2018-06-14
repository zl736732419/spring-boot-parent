package com.zheng.springboot.service.impl;

import com.zheng.springboot.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 14:01 2018/6/14
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello spring boot dubbo";
    }
}
