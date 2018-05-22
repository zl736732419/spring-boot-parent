package com.zheng.springboot.shutdown;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:29
 */
@Component
public class MyDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("disposable bean: destroy");
    }
}
