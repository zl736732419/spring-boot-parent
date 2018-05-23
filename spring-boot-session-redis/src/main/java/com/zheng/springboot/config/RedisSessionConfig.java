package com.zheng.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @Author zhenglian
 * 
 * @Date 2018/5/23 22:07
 */
@Configuration
public class RedisSessionConfig {
    
    @Autowired
    private JedisConfiguration jedisConfiguration;
    
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return jedisConfiguration.convertJedisStandaloneConnectionFactory();
    }
    
}
