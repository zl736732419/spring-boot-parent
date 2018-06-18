package com.zheng.springboot.shiro.utils;

/**
 * @Author zhenglian
 * @Date 2018/6/18 16:07
 */
public class CacheConstant {
    /**
     * 用户登录重试次数
     */
    public static final String RETRY_LIMIT_CACHE = "passwordRetryCache";
    /**
     * 并发登录用户踢出
     */
    public static final String KICK_OUT_SESSION = "shiroKickoutSession";
    
}
