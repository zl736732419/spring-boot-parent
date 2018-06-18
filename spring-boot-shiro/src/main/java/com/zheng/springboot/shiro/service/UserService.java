package com.zheng.springboot.shiro.service;


import com.zheng.springboot.shiro.domain.User;

import java.util.Date;

/**
 * User业务接口
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
public interface UserService extends BaseService<User> {

    /**
     * 锁定用户
     * @param username
     * @param activeTime
     */
    void doLockUser(String username, Date activeTime);

    /**
     * 解除用户锁定
     * @param username
     */
    void doUnlockUser(String username);
    
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}