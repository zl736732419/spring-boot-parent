package com.zheng.springboot.shiro.comps;

import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.enums.EnumUserStatus;
import com.zheng.springboot.shiro.service.UserService;
import com.zheng.springboot.shiro.utils.CacheConstant;
import com.zheng.springboot.shiro.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限制用户密码错误次数的匹配器 将用户输入的错误次数都存放在ehcache方便使用
 *
 * @Author zhenglian
 * @Date 2018/6/16 15:44
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    /**
     * 用户锁定的时间，默认为5分钟
     */
    private Integer lockMinutes = 5;
    
    private Cache<String, AtomicInteger> passwordRetryCache;
    @Resource
    private UserService userService;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) throws CacheException, IOException {
        // 在构造函数中将缓存对象创建好
        passwordRetryCache = cacheManager.getCache(CacheConstant.RETRY_LIMIT_CACHE);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = token.getPrincipal().toString();
        // 开始之前解锁用户
        unlockUser(username);
        
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
        if (isMatcher) {
            // 登录成功删除登录错误次数
            passwordRetryCache.remove(username);
        } else {
            // 登录失败，将登录失败次数+1并放入缓存
            passwordRetryCache.put(username, retryCount);
        }
        return isMatcher;
    }

    /**
     * 当前用户是否锁定，如果锁定并到达解锁时间就需要解锁用户
     * @param username
     */
    private void unlockUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return;
        }
        User user = userService.findByUsername(username);
        if (!Optional.ofNullable(user).isPresent()) {
            return;
        }
        
        Integer status = user.getStatus();
        if (!Objects.equals(status, EnumUserStatus.LOCKED.getKey())) {
            return;
        }
        // 用户被锁定，并在锁定时间内，无法进行登录操作
        Date activeTime = user.getActiveTime();
        if (DateUtil.after(activeTime, new Date())) {
            throw new DisabledAccountException("当前用户已被锁定，请稍后再试");
        }
        userService.doUnlockUser(user.getUsername());
        // 重置缓存中的登录错误计数
        passwordRetryCache.put(username, new AtomicInteger(0));
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
        Date activeTime = DateUtils.addMinutes(new Date(), lockMinutes);
        userService.doLockUser(username, activeTime);
    }

    public Integer getLockMinutes() {
        return lockMinutes;
    }

    public void setLockMinutes(Integer lockMinutes) {
        this.lockMinutes = lockMinutes;
    }
}
