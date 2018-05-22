package com.zheng.springboot.service.impl;

import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;

/**
 * @Author Administrator
 * @Date 2018/5/22 17:41
 */
@CacheDefaults(cacheName = "user")
@Service
public class UserServiceImpl implements UserService {
    @CacheResult
    @Override
    public User findById(Integer userId) {
        return new User(1, "zhangsan", "123456");
    }

    @Override
    public int save(User user) {
        return 1;
    }

    @CacheRemove
    @Override
    public int update(User user) {
        return 0;
    }

    @CacheRemove
    @Override
    public int delete(Integer userId) {
        return 0;
    }
}
