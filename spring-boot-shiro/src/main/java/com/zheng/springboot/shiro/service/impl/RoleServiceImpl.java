package com.zheng.springboot.shiro.service.impl;


import com.zheng.springboot.shiro.comps.ShiroFilterChainManager;
import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.RoleDao;
import com.zheng.springboot.shiro.dao.RoleUrlResourceDao;
import com.zheng.springboot.shiro.dao.UrlResourceDao;
import com.zheng.springboot.shiro.domain.Role;
import com.zheng.springboot.shiro.domain.UrlResource;
import com.zheng.springboot.shiro.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Role业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private UrlResourceDao urlResourceDao;
    @Resource
    private RoleUrlResourceDao roleUrlResourceDao;
    @Resource
    private ShiroFilterChainManager manager;

    @Override
    protected BaseDao<Role> getBaseDao() {
        return roleDao;
    }

    @Override
    public List<Role> findByResourceId(Integer resourceId) {
        if (!Optional.ofNullable(resourceId).isPresent() || resourceId <= 0) {
            return new ArrayList<>();
        }
        
        return roleDao.findByResourceId(resourceId);
    }

    @Override
    public void relateRoleResources(Integer roleId, List<Integer> resourceIds) {
        if (!Optional.ofNullable(roleId).isPresent() || CollectionUtils.isEmpty(resourceIds)) {
            return;
        }
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<UrlResource> dbResources = urlResourceDao.findByRoleIds(roleIds);
        
        // 处理删除的资源
        deleteRoleResources(resourceIds, dbResources);
        // 新增的资源
        newRoleResources(roleId, resourceIds, dbResources);
        // 更新权限信息
    }

    @Override
    public List<Role> findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return new ArrayList<>();
        }
        
        return roleDao.findByUsername(username);
    }

    /**
     * 处理新关联的角色资源
     * @param roleId
     * @param resourceIds 页面传递过来的资源
     * @param dbResources 数据库已经关联的资源
     */
    private void newRoleResources(Integer roleId, List<Integer> resourceIds, List<UrlResource> dbResources) {
        List<Integer> dbResourceIds = dbResources.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .map(UrlResource::getId)
                .collect(Collectors.toList());
        
        List<Integer> newResourceIds = new ArrayList<>();
        resourceIds.stream()
                .filter(item -> Optional.ofNullable(item).isPresent() && !dbResourceIds.contains(item))
                .forEach(item -> newResourceIds.add(item));
        
        if (CollectionUtils.isEmpty(newResourceIds)) {
            return;
        }
        roleUrlResourceDao.addRoleUrlResourceRelation(roleId, newResourceIds);
    }

    /**
     * 处理删除的资源
     * @param resourceIds 页面传递过来的资源
     * @param dbResources 数据库已经关联的资源
     */
    private void deleteRoleResources(List<Integer> resourceIds, List<UrlResource> dbResources) {
        List<Integer> deletedResourceIds = new ArrayList<>();
        dbResources.stream()
                .filter(item -> Optional.ofNullable(item).isPresent() && !resourceIds.contains(item.getId()))
                .forEach(item ->  deletedResourceIds.add(item.getId()));
        if (CollectionUtils.isEmpty(deletedResourceIds)) {
            return;
        }
        urlResourceDao.deleteByIds(deletedResourceIds);
    }
}