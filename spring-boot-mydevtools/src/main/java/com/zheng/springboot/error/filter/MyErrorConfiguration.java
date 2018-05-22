package com.zheng.springboot.error.filter;

import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @Author zhenglian
 * @Date 2018/5/16 18:12
 */
//@Configuration
public class MyErrorConfiguration {
    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new MyErrorPageRegistrar();
    }

    @Bean
    public FilterRegistrationBean my404Filter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new My404Filter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
}
