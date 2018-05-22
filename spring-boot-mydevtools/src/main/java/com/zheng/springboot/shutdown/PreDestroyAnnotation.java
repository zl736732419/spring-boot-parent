package com.zheng.springboot.shutdown;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:30
 */
@Component
public class PreDestroyAnnotation {
    @PreDestroy
    public void cleanUp() {
        System.out.println("PreDestroy Destroy");
    }
}
