package com.zheng.springboot.shiro.service.impl;


import com.zheng.springboot.shiro.comps.ShiroFilterChainManager;
import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.UrlResourceDao;
import com.zheng.springboot.shiro.domain.UrlResource;
import com.zheng.springboot.shiro.service.UrlResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * UrlResource业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class UrlResourceServiceImpl extends BaseServiceImpl<UrlResource> implements UrlResourceService {

    @Resource
    private UrlResourceDao urlResourceDao;
    @Resource
    private ShiroFilterChainManager manager;
    
    @Override
    protected BaseDao<UrlResource> getBaseDao() {
        return urlResourceDao;
    }

    @Override
    public int insert(UrlResource record) {
        int result = super.insert(record);
        manager.initFilterChain();
        return result;
    }

    @Override
    public int deleteById(Integer id) {
        int result = super.deleteById(id);
        manager.initFilterChain();
        return result;
    }

    @Override
    public int update(UrlResource record) {
        int result = super.update(record);
        manager.initFilterChain();
        return result;
    }

    @Override
    public List<UrlResource> findByRoleIds(List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        
        return urlResourceDao.findByRoleIds(roleIds);
    }
}