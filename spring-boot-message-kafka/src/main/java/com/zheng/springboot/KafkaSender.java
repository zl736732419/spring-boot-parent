package com.zheng.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Author Administrator
 * @Date 2018/5/21 15:28
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topic, Object value) {
        ListenableFuture future = kafkaTemplate.send(topic, value);
        kafkaTemplate.flush();
        future.addCallback(o-> System.out.println("消息发送成功: " + value), 
                throwable -> System.out.println("消息发送失败: " + value));
    }

    public void send(String topic, String key, Object value) {
        
        ListenableFuture future = kafkaTemplate.send(topic, key, value);
        kafkaTemplate.flush();
        future.addCallback(o-> System.out.println("消息发送成功: " + value),
                throwable -> System.out.println("消息发送失败: " + value));
    }
}
