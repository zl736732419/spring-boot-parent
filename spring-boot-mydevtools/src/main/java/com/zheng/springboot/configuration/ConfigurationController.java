package com.zheng.springboot.configuration;

import com.zheng.springboot.configuration.yaml.Server;
import com.zheng.springboot.configuration.yaml.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/15 11:34
 */
@RestController
@RequestMapping(value = "/configuration")
public class ConfigurationController {
    
    // 获取配置方式1 @Value
//    @Value("${name}")
    private String name;
    
    // 方式2通过Environment接口来取
    @Autowired
    private Environment environment;
    
    @Autowired
    private Server server;
    
    @Autowired
    private User user;
    
    
    @RequestMapping("/{key}")
    public String test(@PathVariable String key) {
        String value = environment.getProperty(key);
        return value;
    }
    
    @RequestMapping("/server")
    public String server() {
        return server.toString();
    }
    
    @RequestMapping("/user")
    public String user() {
        return user.toString();
    }
}
