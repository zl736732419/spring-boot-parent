package com.zheng.springboot;

import com.zheng.springboot.service.HelloService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author zhenglian
 * @Date 14:25 2018/6/14
 */
public class HelloServiceTest extends BaseServiceTest {

    @Resource
    private HelloService helloService;

    @Test
    public void hello() {
        String hello = helloService.hello();
        System.out.println(hello);
    }

}
