package com.zheng.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author Administrator
 * @Date 2018/5/21 15:32
 */
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void processMessage(ConsumerRecord<?, ?> record) {
        Object value = record.value();
        System.out.println("received: " + record.key() + ":" + value);
    }
}
