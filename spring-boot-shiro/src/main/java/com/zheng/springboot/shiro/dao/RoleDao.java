package com.zheng.springboot.shiro.dao;

import com.zheng.springboot.shiro.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author zhenglian
* @Date 16:30 2018-06-15
*/
@Mapper
public interface RoleDao extends BaseDao<Role> {
    /**
     * 根据资源获取对应角色
     * @param resourceId
     * @return
     */
    List<Role> findByResourceId(@Param("resourceId") Integer resourceId);

    List<Role> findByUsername(@Param("username") String username);
}