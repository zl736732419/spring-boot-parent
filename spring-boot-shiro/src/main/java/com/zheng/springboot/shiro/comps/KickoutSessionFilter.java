package com.zheng.springboot.shiro.comps;

import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.utils.CacheConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * 限制统一用户并发登录
 *
 * @Author zhenglian
 * @Date 2018/6/18 15:12
 */
public class KickoutSessionFilter extends AccessControlFilter {
    /**
     * 每一个用户并发登录的最大次数
     */
    private Integer maxSession = 1;

    /**
     * 被下线的用户重定向的url连接地址
     */
    private String kickoutUrl;
    /**
     * 下线的是先登录的用户还是后登陆的用户
     * 默认是先登陆的用户
     */
    private boolean kickoutAfter = false;

    /**
     * 记录并发登录的用户，存放每个并发登录用户的sessionid
     */
    private Cache<String, Deque<Serializable>> cache;
    /**
     * 踢出用户session标记的属性
     */
    public static final String KICK_OUT_ATTR = "kickout";
    
    @Resource
    private SessionManager sessionManager;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        // 并发登录需要用户已经认证,还没进行认证的请求直接通过
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }

        Session session = subject.getSession();
        Serializable sessionId = session.getId();

        // 统一处理标记的踢出用户session，踢出标记的用户
        boolean result = kickoutSession(subject, session, request, response);
        // 针对当前登录用户，所以，如果被踢出就不需要进行下面的操作
        if (!result) {
            return false;
        }
        
        User user = (User) subject.getPrincipal();
        String username = user.getUsername();
        // 将当前session加入并发登录队列中
        Deque<Serializable> deque = addToConcurrentDeque(username, sessionId);
        // 并发踢出用户,这里只是取出对应的用户session并进行标记
        markKickoutSession(deque);
        return true;
    }

    /**
     * 踢出标记用户
     * @param subject
     * @param session
     * @param request
     * @param response
     */
    private boolean kickoutSession(Subject subject, Session session, ServletRequest request, ServletResponse response) throws IOException {
        if (!Optional.ofNullable(session).isPresent()) {
            return true;
        }
        Object kickoutAttr = session.getAttribute(KICK_OUT_ATTR);
        if (!Optional.ofNullable(kickoutAttr).isPresent()) {
            return true;
        }
        saveRequest(request);
        request.setAttribute("error", "你已被踢出");
        WebUtils.issueRedirect(request, response, kickoutUrl);
        subject.logout();
        return false;
    }

    /**
     * 标记将要被踢出的用户
     * @param deque
     */
    private void markKickoutSession(Deque<Serializable> deque) {
        if (CollectionUtils.isEmpty(deque)) {
            return;
        }
        Serializable kickoutSessionId;
        Session kickoutSession;
        while (deque.size() > maxSession && !deque.isEmpty()) {
            if (kickoutAfter) {
                kickoutSessionId = deque.removeLast();
            } else {
                kickoutSessionId = deque.removeFirst();
            }
            try {
                kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            } catch(SessionException e) {
                kickoutSession = null;
            }

            // logout之后，session已经被移除，所以这里就无须在进行处理
            if (!Optional.ofNullable(kickoutSession).isPresent()) {
                continue;
            }

            if (Optional.ofNullable(kickoutSession).isPresent()) {
                kickoutSession.setAttribute(KICK_OUT_ATTR, "true");
            }
        }
    }

    /**
     * 将当前用户添加到并发登录用户队列中
     * @param username
     * @param sessionId
     * @return
     */
    private Deque<Serializable> addToConcurrentDeque(String username, Serializable sessionId) {
        if (StringUtils.isEmpty(username) || !Optional.ofNullable(sessionId).isPresent()) {
            return new LinkedList<>();
        }
        Deque<Serializable> deque = cache.get(username);
        if (!Optional.ofNullable(deque).isPresent()) {
            deque = new LinkedList<>();
            cache.put(username, deque);
        }

        // 如果没有记录当前登录的用户session就新添加成员到队尾
        if (!deque.contains(sessionId)) {
            deque.add(sessionId);
        }
        return deque;
    }

    public Integer getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(Integer maxSession) {
        this.maxSession = maxSession;
    }

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setCacheManager(CacheManager cacheManager) {
        cache = cacheManager.getCache(CacheConstant.KICK_OUT_SESSION);
    }
}
