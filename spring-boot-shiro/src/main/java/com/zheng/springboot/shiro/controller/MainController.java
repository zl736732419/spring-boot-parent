package com.zheng.springboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录成功后的页面，这里只是一个测试
 * @Author zhenglian
 * @Date 2018/6/18 11:22
 */
@Controller
public class MainController extends BaseController {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        putRequestContext("user", getUser());
        return "list";
    }
}
