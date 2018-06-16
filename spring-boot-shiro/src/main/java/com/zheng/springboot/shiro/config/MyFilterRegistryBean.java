package com.zheng.springboot.shiro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * filter url-pattern设置为/*标识对所有请求进行拦截，包括jsp和静态资源
 * @Author zhenglian
 * @Date 2018/5/17 13:41
 */
@Configuration
public class MyFilterRegistryBean {
    /**
     * shiro过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean shiroFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
        shiroFilter.setTargetFilterLifecycle(true);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
