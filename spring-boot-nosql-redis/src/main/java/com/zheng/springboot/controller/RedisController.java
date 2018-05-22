package com.zheng.springboot.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhenglian
 * @Date 2018/5/18 11:23
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource(name = "standaloneRedisTemplate")
    private RedisTemplate<String, String> template;
    
    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        String value = template.opsForValue().get(key);
        return value;
    }
    
    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value) {
        template.opsForValue().set(key, value);
        return "ok";
    }
    
    @GetMapping("/delete/{key}")
    public String delete(@PathVariable String key) {
        template.delete(key);
        return "ok";
    }
    
    @GetMapping("/add/{key}/{value}")
    public String add(@PathVariable String key, @PathVariable String value) {
        template.opsForList().rightPush(key, value);
        return "ok";
    }
    
    @GetMapping("/list/{key}")
    public List<String> list(@PathVariable String key) {
        List<String> elements = template.opsForList().range(key, 0, -1);
        return elements;
    }
}
