package com.zheng.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/23 22:12
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @GetMapping("/hello/{str}")
    public String hello(@PathVariable String str, HttpServletRequest request) {
        String key = "springboot";
        HttpSession session = request.getSession();
        Object o = session.getAttribute(key);
        if (!Optional.ofNullable(o).isPresent()) {
            o = "spring boot session test";
            session.setAttribute(key, o);
        } else {
            System.out.println(o);
        }

        // 通过username查找到session
        String username = "zhangsan";
        session.setAttribute(
                FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        
        return "sessionId:" + session.getId();
    }

    @GetMapping("/findByUsername/{username}")
    public Map findByUsername(@PathVariable String username) {
        Map<String, ? extends Session> usersSessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        return usersSessions;
    }
}
