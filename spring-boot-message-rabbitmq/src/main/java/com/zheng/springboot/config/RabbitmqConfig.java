package com.zheng.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhenglian
 * @Date 2018/5/20 10:36
 */
@Configuration
public class RabbitmqConfig {

    public static final String TOPIC_MESSAGE_QUEUE = "topic.message"; 
    public static final String TOPIC_EXCHANGE = "exchange";
    
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    
    /**
     * 创建queue与默认的exchange进行绑定，采用direct模式，routingkey=hello
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
    
    @Bean
    public Queue topicMessageQueue() {
        return new Queue(TOPIC_MESSAGE_QUEUE);
    }
    
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    
    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicMessageQueue())
                .to(topicExchange())
                .with("topic.#");
    }
    
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE2);
    }
    
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    
    @Bean
    public Binding bindFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
