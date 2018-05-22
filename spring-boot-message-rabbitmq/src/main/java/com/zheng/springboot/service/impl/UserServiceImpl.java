package com.zheng.springboot.service.impl;

import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @Date 2018/5/22 17:41
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer userId) {
        return new User(1, "zhangsan", "123456");
    }

    @Override
    public int save(User user) {
        return 1;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(Integer userId) {
        return 0;
    }
}
