package com.zheng.springboot.shiro.service.impl;


import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.UserDao;
import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.enums.EnumUserStatus;
import com.zheng.springboot.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * User业务逻辑实现
 *
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

    @Override
    public void doLockUser(String username, Date activeTime) {
        if (StringUtils.isEmpty(username)
                || !Optional.ofNullable(activeTime).isPresent()) {
            return;
        }
        User user = userDao.findByUsername(username);
        if (!Optional.ofNullable(user).isPresent()) {
            throw new UnknownAccountException("未知的用户名【" + username + "】");
        }
        user.setActiveTime(activeTime);
        user.setStatus(EnumUserStatus.LOCKED.getKey());
        userDao.update(user);
    }

    @Override
    public void doUnlockUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        User user = userDao.findByUsername(username);
        if (!Optional.ofNullable(user).isPresent()) {
            throw new UnknownAccountException("未知的用户名【" + username + "】");
        }
        user.setStatus(EnumUserStatus.OK.getKey());
        userDao.update(user);
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        
        return userDao.findByUsername(username);
    }
}