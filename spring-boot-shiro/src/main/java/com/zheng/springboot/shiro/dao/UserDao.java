package com.zheng.springboot.shiro.dao;

import com.zheng.springboot.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @Author zhenglian
* @Date 16:30 2018-06-15
*/
@Mapper
public interface UserDao extends BaseDao<User> {
    /**
     * 根据用户名查找(用户名唯一标识用户)
     * @param username
     * @return
     */
    User findByUsername(@Param("username") String username);
}