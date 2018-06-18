package com.zheng.springboot.shiro.comps;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 继承logout，当登出同时，将当前session设置失效
 * @Author zhenglian
 * @Date 2018/6/18 11:34
 */
public class MyLogoutFilter extends LogoutFilter {
    private PatternMatcher pathMatcher = new AntPathMatcher();
    private static final String DEFAULT_LOGOUT_URL = "/logout";
    private String logoutUrl = DEFAULT_LOGOUT_URL;
    
    private boolean pathsMatch(String pattern, String path) {
        return pathMatcher.matches(pattern, path);
    }
    
    private String getPathWithinApplication(ServletRequest request) {
        return WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
    }
    
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String requestURI = getPathWithinApplication(request);
        boolean isMatch = pathsMatch(getLogoutUrl(), requestURI);
        // 不是logout请求，直接通过
        if (!isMatch) {
            return true;
        }
        
        boolean result = super.preHandle(request, response);
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
        }
        return result;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }
}
