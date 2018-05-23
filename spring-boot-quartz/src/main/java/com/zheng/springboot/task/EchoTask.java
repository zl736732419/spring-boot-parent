package com.zheng.springboot.task;

import com.zheng.springboot.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author Administrator
 * @Date 2018/5/23 15:29
 */
@Component
public class EchoTask {
    
    @Autowired
    private EchoService echoService;

    /**
     *     // 上一次开始执行时间点之后5秒再执行
     *     @Scheduled(fixedRate = 5000)
     *     // 上一次执行完毕时间点之后5秒再执行
     *     @Scheduled(fixedDelay = 5000)
     *     // 第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     *     @Scheduled(initialDelay=1000, fixedRate=5000)
     */
    @Scheduled(cron = "* * * * * *")
    public void echo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String result = echoService.echo(df.format(now));
        System.out.println(result);
    }
}
