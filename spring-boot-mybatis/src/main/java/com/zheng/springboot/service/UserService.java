package com.zheng.springboot.service;


import com.zheng.springboot.domain.MyPageBounds;
import com.zheng.springboot.domain.MyPageList;
import com.zheng.springboot.domain.User;
import com.zheng.springboot.filter.UserFilter;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:05
 */
public interface UserService {
    User findById(Integer id);
    int save(User user);
    int deleteById(Integer id);
    int update(User user);
    MyPageList<User> findByPage(UserFilter userFilter, MyPageBounds pageBounds);
}
