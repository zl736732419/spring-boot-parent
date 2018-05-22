package com.zheng.springboot.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取ctx
 * @Author zhenglian
 * @Date 2018/5/14 17:40
 */
@Component
public class MyApplicationContextAware implements ApplicationContextAware {
    public static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
