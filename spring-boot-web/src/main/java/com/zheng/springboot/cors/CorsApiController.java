package com.zheng.springboot.cors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器端接口
 * @Author zhenglian
 * @Date 2018/5/17 8:58
 */
@RestController
@RequestMapping("/api")
public class CorsApiController {
 
    @GetMapping(value = "/get")
    public String get() {
        return "get";
    }
}
