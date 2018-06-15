package com.zheng.springboot.service.shiro.impl;


import com.zheng.springboot.shiro.dao.BaseDao;
import com.zheng.springboot.shiro.dao.UrlResourceDao;
import com.zheng.springboot.shiro.domain.UrlResource;
import com.zheng.springboot.shiro.service.UrlResourceService;
import com.zheng.springboot.shiro.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UrlResource业务逻辑实现
 * @Author zhenglian
 * @Date 16:30 2018-06-15
 */
@Service
public class UrlResourceServiceImpl extends BaseServiceImpl<UrlResource> implements UrlResourceService {

    @Resource
    private UrlResourceDao urlResourceDao;

    @Override
    protected BaseDao<UrlResource> getBaseDao() {
        return urlResourceDao;
    }
}