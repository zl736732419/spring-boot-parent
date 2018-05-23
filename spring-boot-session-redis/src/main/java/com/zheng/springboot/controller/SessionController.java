package com.zheng.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/23 22:12
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/hello/{str}")
    public String hello(@PathVariable String str, HttpServletRequest request) {
        String key = "springboot";
        HttpSession session = request.getSession();
        Object o = session.getAttribute(key);
        if (!Optional.ofNullable(o).isPresent()) {
            o = "spring boot session test";
            session.setAttribute(key, o);
        }
        return "sessionId:" + session.getId();
    }
}
