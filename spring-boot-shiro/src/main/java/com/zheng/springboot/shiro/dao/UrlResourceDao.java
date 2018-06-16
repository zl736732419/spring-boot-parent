package com.zheng.springboot.shiro.dao;

import com.zheng.springboot.shiro.domain.UrlResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author zhenglian
* @Date 16:30 2018-06-15
*/
@Mapper
public interface UrlResourceDao extends BaseDao<UrlResource> {

    /**
     * 删除指定的资源
     * @param deletedResourceIds
     */
    void deleteByIds(@Param("resourceIds") List<Integer> deletedResourceIds);

    /**
     * 查询指定角色拥有的权限
     * @param roleIds
     * @return
     */
    List<UrlResource> findByRoleIds(@Param("roleIds") List<Integer> roleIds);
}