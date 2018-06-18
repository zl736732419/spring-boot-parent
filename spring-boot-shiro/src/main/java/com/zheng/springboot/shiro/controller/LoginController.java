package com.zheng.springboot.shiro.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author zhenglian
 * @Date 2018/6/18 9:52
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 登录操作
     * 所有认证操作都在shiro中完成，如果认证失败，会在request中通过shiroLoginFailure记录错误异常
     * 否则表示认证成功
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        String shiroLoginFailure = (String) getRequest().getAttribute("shiroLoginFailure");
        if (StringUtils.isEmpty(shiroLoginFailure)) {
            return "redirect:/list";
        }

        String error;
        if(shiroLoginFailure.equals(UnknownAccountException.class.getName())) {
            error = "用户名不存在!";
        }else if(shiroLoginFailure.equals(LockedAccountException.class.getName())) {
            error = "当前用户已被禁用!";
        }else if(shiroLoginFailure.equals(IncorrectCredentialsException.class.getName())) {
            error = "用户名/密码错误!";
        }else if(shiroLoginFailure.equals(ExcessiveAttemptsException.class.getName())) {
            error = "登录次数过于频繁，请稍后再试!";
        }else if(shiroLoginFailure.equals(ExpiredCredentialsException.class.getName())) {
            error = "密码已失效!";
        }else {
            error = "遇到错误!";
        }
        putRequestContext("error", error);
        return "login";
    }

    /**
     * 访问未授权页面
     * @return
     */
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized() {
        return "unauthorized";
    }
    
}
