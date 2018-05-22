package com.zheng.springboot.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @Author zhenglian
 * @Date 2018/5/17 13:47
 */
@Configuration
public class MyServletRegistryBean {
    
    @Bean
    public ServletRegistrationBean myServlet01() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new MyServlet01());
        bean.addUrlMappings("/servlet01");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 101);
        return bean;
    }
}
