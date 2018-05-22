package com.zheng.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 需要修改kafka配置文件：${kafka_home}/config/server.properties
 * advertised.listeners=PLAINTEXT://192.168.1.52:9092 # 52为kafka服务器的地址
 * @Author Administrator
 * @Date 2018/5/21 15:22
 */
@Configuration
@EnableKafka
public class KafkaTopicConfig {
    
    @Value("${spring.kafka.topic.num-partitions}")
    private Integer numPartitions;
    @Value("${spring.kafka.topic.replication-factor}")
    private Short replicationFactor;
    @Value("${kafka.consumer.topic}")
    private String topicName;

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(topicName, numPartitions, replicationFactor);
    }
}
