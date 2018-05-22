package com.zheng.springboot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 配置启用ehcache
 * @Author zhenglian
 * @Date 2018/5/18 17:41
 */
@Configuration
@EnableCaching
public class Ehcache2Config {
    
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache2.xml"));
        factory.setShared(true);
        factory.afterPropertiesSet();
        return factory;
    }
    
    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
        return cacheManager;
    }
}
