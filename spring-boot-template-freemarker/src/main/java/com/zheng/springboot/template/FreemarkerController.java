package com.zheng.springboot.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author zhenglian
 * @Date 2018/5/16 16:28
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {
    @GetMapping(value = "/hello")
    public String helloFtl(Map<String, Object> map) {
        map.put("hello", "from FreemarkerController.helloFtl");
        return "hello";
    }
}

