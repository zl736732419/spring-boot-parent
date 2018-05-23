package com.zheng.springboot.service.impl;

import com.zheng.springboot.service.EchoService;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @Date 2018/5/23 15:21
 */
@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String str) {
        return "echo: " + str;
    }
}
