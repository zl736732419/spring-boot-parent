package com.zheng.springboot.shiro.dao;

import com.zheng.springboot.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @Author zhenglian
* @Date 16:30 2018-06-15
*/
@Mapper
public interface UserDao extends BaseDao<User> {
}