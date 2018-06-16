package com.zheng.springboot.shiro.comps;

import com.zheng.springboot.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限制用户密码错误次数的匹配器 将用户输入的错误次数都存放在ehcache方便使用
 *
 * @Author zhenglian
 * @Date 2018/6/16 15:44
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Cache<String, AtomicInteger> passwordRetryCache;
    @Resource
    private UserService userService;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) throws CacheException, IOException {
        // 在构造函数中将缓存对象创建好
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = token.getPrincipal().toString();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        // 初次使用，实例化一个记录用户输入错误密码的对象并放入到缓存中
        if (!Optional.ofNullable(retryCount).isPresent()) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }

        // 获取当前用户输入密码错误次数，如果超过5次就抛出异常
        if (retryCount.incrementAndGet() > 5) {
            lockUser(username);
            throw new ExcessiveAttemptsException("您输入密码错误次数太多，系统将暂时锁定用户，请30分钟后再试!");
        }

        boolean isMatcher = super.doCredentialsMatch(token, info);
        return isMatcher;
    }

    /**
     * 锁定当前用户
     *
     * @param username
     */
    private void lockUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        // 锁定5分钟
        int lockMinutes = 5;
        Date activeTime = DateUtils.addMinutes(new Date(), lockMinutes);
        userService.doLockUser(username, activeTime);
    }
}
