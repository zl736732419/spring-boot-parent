package com.zheng.springboot.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @Author zhenglian
 * @Date 2018/5/17 14:01
 */
@Configuration
public class MyListenerRegistryBean {
    @Bean
    public ServletListenerRegistrationBean myListener01() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new MyListener01());
        bean.setEnabled(true);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 50);
        return bean;
    }
}
