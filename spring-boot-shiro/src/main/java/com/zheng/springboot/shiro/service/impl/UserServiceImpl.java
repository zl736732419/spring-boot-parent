package com.zheng.springboot.shiro.service.impl;


import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.UserDao;
import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    protected BaseDao<User> getBaseDao() {
        return userDao;
    }
}