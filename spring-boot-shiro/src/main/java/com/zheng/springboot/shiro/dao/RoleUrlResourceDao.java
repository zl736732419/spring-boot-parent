package com.zheng.springboot.shiro.dao;
import com.zheng.springboot.shiro.domain.RoleUrlResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色资源关联数据接口
* @Author zhenglian
* @Date 10:30 2018-06-16
*/
@Mapper
public interface RoleUrlResourceDao extends BaseDao<RoleUrlResource> {
    /**
     * 添加新的角色资源关联
     * @param roleId
     * @param newResourceIds
     */
    void addRoleUrlResourceRelation(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> newResourceIds);
}