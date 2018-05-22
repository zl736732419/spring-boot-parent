package com.zheng.springboot.service.impl;

import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.RedisCacheService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author zhenglian
 * @Date 2018/5/19 14:36
 */
@Service
@CacheConfig(cacheNames = "redis-cache")
public class RedisCacheServiceImpl implements RedisCacheService {
    @Cacheable(value="redis-cache::msg", key = "#msg")
    @Override
    public String hello(String msg) {
        return "redis cache: " + msg;
    }

    // 最后缓存结果：redis-cache::user::userId::1->
    // "[\"com.zheng.springboot.domain.User\",{\"userId\":1,\"username\":\"xiaolian\",\"password\":\"123456\"}]"
    @Cacheable(value = "redis-cache::user::userId", key = "#userId")
    @Override
    public User findUser(Integer userId) {
        return new User(1, "xiaolian", "123456");
    }
}
