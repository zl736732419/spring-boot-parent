package com.zheng.springboot;

import com.zheng.springboot.domain.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Administrator
 * @Date 2018/5/21 10:26
 */
public class ActivemqTest extends BaseServiceTest {
    @Autowired
    private Producer producer;
    
    @Value("${custom.activemq.queue}")
    private String queueName;
    @Value("${custom.activemq.topic}")
    private String topicName;
    private ActiveMQQueue queue;
    private ActiveMQTopic topic;
    
    @Before
    public void init() {
        queue = new ActiveMQQueue(queueName);
        topic = new ActiveMQTopic(topicName);
    }
    
    @Test
    public void sendQueueTextMessage() {
        producer.sendMessage(queue, "helloworld activemq queue");
    }

    @Test
    public void sendQueueObjectMessage() {
        producer.sendMessage(queue, new User("zhangsan", "123456", 25));
    }
    
    @Test
    public void sendTopicTextMessage() {
        producer.sendMessage(topic, "helloworld activemq topic");
    }

    @Test
    public void sendTopicObjectMessage() {
        producer.sendMessage(topic, new User("王二麻子", "123456", 26));
    }
    
}
