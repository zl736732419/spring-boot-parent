package com.zheng.springboot.shiro;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.junit.Test;

/**
 * @Author zhenglian
 * @Date 10:03 2018/6/15
 */
public class PasswordServiceTest {

    @Test
    public void encryPwd() {
        String password = "123456";
        PasswordService passwordService = new DefaultPasswordService();
        String encryptPwd = passwordService.encryptPassword(password);
        System.out.println(encryptPwd);
    }


}
