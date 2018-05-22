package com.zheng.springboot.echo.service.impl;

import com.zheng.springboot.echo.service.EchoService;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 2018/5/14 11:26
 */
@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String str) {
        String echo = "hello world echo: " + str;
        return echo;
    }
}
