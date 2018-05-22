package com.zheng.springboot.echo;


import com.zheng.springboot.echo.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenglian
 * @Date 2018/5/14 11:28
 */
@RestController
@RequestMapping("/echo")
public class EchoController {
    @Autowired
    private EchoService echoService;

    @RequestMapping("/{msg}")
    public String echo(@PathVariable String msg) {
        String echo = echoService.echo(msg);
        return echo;
    }
}
