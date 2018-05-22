package com.zheng.springboot.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author zhenglian
 * @Date 2018/5/16 16:14
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping(value = "/hello")
    public String helloHtml(Map<String, Object> map) {
        map.put("hello", "from ThymeleafController.helloHtml");
        return "hello";
    }
}
