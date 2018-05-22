package com.zheng.springboot.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * filter url-pattern设置为/*标识对所有请求进行拦截，包括jsp和静态资源
 * @Author zhenglian
 * @Date 2018/5/17 13:41
 */
@Configuration
public class MyFilterRegistryBean {
    @Bean
    public FilterRegistrationBean myFilter1() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new MyFilter01());
        bean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE + 101);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
