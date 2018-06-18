package com.zheng.springboot.shiro.controller;

import com.zheng.springboot.shiro.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BaseController {
	protected HttpServletResponse response;
	protected HttpServletRequest request;

	@ModelAttribute
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@ModelAttribute
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	protected void putRequestContext(String key, Object value) {
		this.request.setAttribute(key, value);
	}

	protected void putSessionContext(String key, Object value) {
		this.request.getSession().setAttribute(key, value);
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	@SuppressWarnings("unchecked")
	protected User getUser() {
		PrincipalCollection ps = getSubject().getPrincipals();
		List<Object> list = ps.asList();
		if (CollectionUtils.isEmpty(list)) {
		    return null;
		}
		System.out.println(list);
		User user = (User) list.get(0);
		return user;
	}

}