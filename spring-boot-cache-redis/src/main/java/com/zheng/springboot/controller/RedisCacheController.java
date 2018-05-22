package com.zheng.springboot.controller;


import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/19 14:37
 */
@RestController
@RequestMapping("/redis/cache")
public class RedisCacheController {
    @Autowired
    private RedisCacheService redisCacheService;
    
    @GetMapping("/{value}")
    public String hello(@PathVariable String value) {
        return redisCacheService.hello(value);    
    }

    @GetMapping("/user/{userId}")
    public User findUser(@PathVariable Integer userId) {
        return redisCacheService.findUser(userId);
    }
    
}
