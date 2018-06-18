package com.zheng.springboot.shiro.utils;

import com.zheng.springboot.shiro.domain.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/6/16 17:18
 */
@Component
public class PasswordUtil {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    @Value("${credential.hash.algorithm}")
    private String algorithmName;
    @Value("${credential.hash.iteration}")
    private int hashIterations;

    /**
     * 密码加密
     *
     * @author zhenglian
     * @data 2016年1月20日 下午8:04:28
     * @param user
     */
    public void encryptPassword(User user) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
}
