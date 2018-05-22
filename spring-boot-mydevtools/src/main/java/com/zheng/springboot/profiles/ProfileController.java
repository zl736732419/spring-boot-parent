package com.zheng.springboot.profiles;

/**
 * @Author zhenglian
 * @Date 2018/5/16 9:12
 */

import com.zheng.springboot.profiles.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/16 9:09
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return helloService.hi();
    }
}