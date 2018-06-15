package com.zheng.springboot.shiro.service.impl;


import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.RoleDao;
import com.zheng.springboot.shiro.domain.Role;
import com.zheng.springboot.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Role业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    protected BaseDao<Role> getBaseDao() {
        return roleDao;
    }
}