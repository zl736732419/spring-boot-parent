package com.zheng.springboot.ctxinitialize;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 访问servlet context
 * 启动的时候执行
 * @Author zhenglian
 * @Date 2018/5/17 14:08
 */
@Component
public class MyServletContextInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("context initializer: " + servletContext.getContextPath());
    }
}
