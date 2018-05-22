package com.zheng.springboot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:32
 */
public class RabbitmqTest extends BaseServiceTest {
    @Autowired
    private RabbitmqSender rabbitmqSender;
    
    @Test
    public void hello() {
        rabbitmqSender.hello();
    }

    @Test
    public void user() {
        rabbitmqSender.user(2);
    }
    
    @Test
    public void topic() {
        rabbitmqSender.topic(3);
    }
    
    @Test
    public void fanoutMessage() {
        rabbitmqSender.fanoutMessage(3);
    }
}
