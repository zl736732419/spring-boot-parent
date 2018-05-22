package com.zheng.springboot.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * 需要使用@ServletComponentScan
 * @Author zhenglian
 * @Date 2018/5/17 13:55
 */
@WebListener
public class MyListener02 implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("MyListener02: request destroy");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyListener02: request init");
    }
}
