package com.zheng.springboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登出控制器
 */
@Controller
@RequestMapping("/logout")
public class LogoutController extends BaseController{

	@RequestMapping(method=RequestMethod.GET)
	public String logout() {
		getSession().invalidate();
		return "/login";
	}
}