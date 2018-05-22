package com.zheng.springboot.service;

import com.zheng.springboot.domain.User;

/**
 * @Author Administrator
 * @Date 2018/5/22 17:41
 */
public interface UserService {
    User findById(Integer userId);
    int save(User user);
    int update(User user);
    int delete(Integer userId);
}
