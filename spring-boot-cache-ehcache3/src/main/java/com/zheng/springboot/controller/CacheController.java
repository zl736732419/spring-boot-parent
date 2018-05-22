package com.zheng.springboot.controller;

import com.zheng.springboot.domain.User;
import com.zheng.springboot.service.MathService;
import com.zheng.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/18 16:13
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    
    @Autowired
    private MathService mathService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/pi/{num}")
    public Object pi(@PathVariable Integer num) {
        return mathService.computePiDecimal(num);
    }

    @GetMapping("/user/find/{userId}")
    public User findUser(@PathVariable Integer userId) {
        return userService.findById(userId);
    }
    
    @GetMapping("/user/update/{userId}")
    public String updateUser(@PathVariable Integer userId) {
        User user = new User();
        user.setUserId(userId);
        userService.update(user);
        return "ok";
    }
}
