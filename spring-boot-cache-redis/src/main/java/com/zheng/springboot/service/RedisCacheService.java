package com.zheng.springboot.service;

import com.zheng.springboot.domain.User;

/**
 * @Author zhenglian
 * @Date 2018/5/19 14:35
 */
public interface RedisCacheService {
    String hello(String msg);

    User findUser(Integer userId);
}
