package com.zheng.springboot;

import com.zheng.springboot.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Administrator
 * @Date 2018/5/21 15:33
 */
public class KfakaTest extends BaseServiceTest {
    
    @Value("${kafka.consumer.topic}")
    private String topicName;
    
    @Autowired
    private KafkaSender kafkaSender;
    
    @Test
    public void test() {
        kafkaSender.send(topicName,  "what how where");
    }
    
    @Test
    public void testKV() {
        kafkaSender.send(topicName, "hello", "what the fuck!");
    }

    @Test
    public void testKVObject() {
        kafkaSender.send(topicName, "hello", new User("zhangsan", "123456", 25));
    }

}
