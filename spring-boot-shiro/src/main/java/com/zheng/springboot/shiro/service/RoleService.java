package com.zheng.springboot.shiro.service;


import com.zheng.springboot.shiro.domain.Role;

import java.util.List;

/**
 * Role业务接口
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据资源获取对应角色
     * @param resourceId
     * @return
     */
    List<Role> findByResourceId(Integer resourceId);

    /**
     * 关联角色与权限
     * 这里关联和取消关联放在一起实现
     * @param roleId
     * @param resourceIds
     */
    void relateRoleResources(Integer roleId, List<Integer> resourceIds);

    /**
     * 根据用户名查找角色
     * @param username
     * @return
     */
    List<Role> findByUsername(String username);
}