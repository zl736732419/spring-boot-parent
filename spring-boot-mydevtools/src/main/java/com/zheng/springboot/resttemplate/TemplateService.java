package com.zheng.springboot.resttemplate;


import com.zheng.springboot.domain.User;

/**
 * @Author zhenglian
 * @Date 2018/5/21 22:38
 */
public interface TemplateService {
    User getUser(Integer userId);
}
