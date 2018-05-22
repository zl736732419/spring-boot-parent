package com.zheng.springboot.resttemplate;


import com.zheng.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采用rest template在程序中远程调用rest服务提供的接口
 *  这里提供的User其实是调用的spring-boot-mybatis项目的db/user/find/{userId}接口得来的
 * @Author zhenglian
 * @Date 2018/5/21 22:36
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    
    @Autowired
    private TemplateService templateService;
    
    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return templateService.getUser(userId);
    }
    
}
