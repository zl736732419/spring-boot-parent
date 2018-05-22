package com.zheng.springboot.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里最好是扩展BaseErrorController，添加对Accept为其他类型的错误处理方式
 * @Author zhenglian
 * @Date 2018/5/16 16:58
 */
@RestController
@RequestMapping("/error")
public class MyErrorController {

    @GetMapping(value = "/arithmetic")
    public String error() {
        int a = 1/0;
        return "error";
    }

}
