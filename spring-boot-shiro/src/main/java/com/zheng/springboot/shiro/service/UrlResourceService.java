package com.zheng.springboot.shiro.service;


import com.zheng.springboot.shiro.domain.UrlResource;

import java.util.List;

/**
 * UrlResource业务接口
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
public interface UrlResourceService extends BaseService<UrlResource> {

    /**
     * 查询指定角色所对应的权限
     * @param roleIds
     * @return
     */
    List<UrlResource> findByRoleIds(List<Integer> roleIds);
}