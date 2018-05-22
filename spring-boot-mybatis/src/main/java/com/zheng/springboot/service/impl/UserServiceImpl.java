package com.zheng.springboot.service.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zheng.springboot.constants.DefaultConstants;
import com.zheng.springboot.domain.MyPageBounds;
import com.zheng.springboot.domain.MyPageList;
import com.zheng.springboot.domain.User;
import com.zheng.springboot.filter.UserFilter;
import com.zheng.springboot.mapper.UserMapper;
import com.zheng.springboot.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DefaultConstants defaultConstants;
    
    @Autowired
    private SqlSession session;
    
    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }

    /**
     * 添加用户时，会同时添加到缓存中
     * @param user
     * @return
     */
    @Override
    public int save(User user) {
        if (!Optional.ofNullable(user).isPresent()) {
            return 0;
        }
        return userMapper.save(user);
    }

    /**
     * 删除用户时会删除缓存中的记录
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        if (!Optional.ofNullable(id).isPresent()) {
            return 0;
        }
        return userMapper.deleteById(id);
    }

    /**
     * 更新时会同时更新缓存中的结果
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        if (!Optional.ofNullable(user).isPresent()) {
            return 0;
        }
        return userMapper.update(user);
    }

    @Override
    public MyPageList<User> findByPage(UserFilter userFilter, MyPageBounds pageBounds) {
        if (!Optional.ofNullable(userFilter).isPresent()) {
            userFilter = new UserFilter();
        }
        if (!Optional.ofNullable(pageBounds).isPresent()) {
            pageBounds = new MyPageBounds(defaultConstants.defaultPage, defaultConstants.defaultLimit);
        }

        PageList<User> pageList = userMapper.findByPage(userFilter, pageBounds.getPageBounds());
        if (CollectionUtils.isEmpty(pageList)) {
            return null;
        }
        
        return new MyPageList<>(pageList);
    }
}
