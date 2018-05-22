package com.zheng.springboot.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;

/**
 * @Author Administrator
 * @Date 2018/5/21 10:53
 */
@Configuration
@EnableJms
public class ActivemqConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.user}")
    private String username;
    @Value("${spring.activemq.password}")
    private String password;
    @Value("${spring.activemq.redelivery.useExponentialBackOff}")
    private Boolean useExponentialBackOff;
    @Value("${spring.activemq.redelivery.maximumRedeliveryDelay}")
    private Long maximumRedeliveryDelay;
    @Value("${spring.activemq.redelivery.maximumRedeliveries}")
    private Integer maximumRedeliveries;
    @Value("${spring.activemq.redelivery.initialRedeliveryDelay}")
    private Long initialRedeliveryDelay;
    @Value("${spring.activemq.redelivery.backOffMultiplier}")
    private Double backOffMultiplier;
    @Value("${spring.activemq.redelivery.useCollisionAvoidance}")
    private Boolean useCollisionAvoidance;
    @Value("${spring.activemq.acknowledge-mode}")
    private Integer acknowledgeMode;
    
    /**
     * 消息重试配置项 
     * @return
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        // 是否在每次失败重发时，增长等待时间  
        redeliveryPolicy.setUseExponentialBackOff(useExponentialBackOff);
        // 设置重发最大拖延时间，-1 表示没有拖延，只有setUseExponentialBackOff(true)时生效  
        redeliveryPolicy.setMaximumRedeliveryDelay(maximumRedeliveryDelay);
        // 重发次数  
        redeliveryPolicy.setMaximumRedeliveries(maximumRedeliveries);
        // 重发时间间隔  
        redeliveryPolicy.setInitialRedeliveryDelay(initialRedeliveryDelay);
        // 第一次失败后重发前等待500毫秒，第二次500*2，依次递增  
        redeliveryPolicy.setBackOffMultiplier(backOffMultiplier);
        // 是否避免消息碰撞  
        redeliveryPolicy.setUseCollisionAvoidance(useCollisionAvoidance);
        return redeliveryPolicy;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(RedeliveryPolicy redeliveryPolicy){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        factory.setRedeliveryPolicy(redeliveryPolicy);
        // activemq默认实现认为其他包自定义的bean是不安全的，只允许特定包的对象能够被传输，这里需要告知activemq实现信任我们定义的bean
        factory.setTrustAllPackages(true);
        return factory;
    }
    
    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //设置持久化，1 非持久， 2 持久化  
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setConnectionFactory(factory);
        //消息确认模式  
        // SESSION_TRANSACTED = 0  事物提交并确认
        // AUTO_ACKNOWLEDGE = 1    自动确认
        // CLIENT_ACKNOWLEDGE = 2    客户端手动确认
        // DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
        // INDIVIDUAL_ACKNOWLEDGE = 4    单条消息确认
        jmsTemplate.setSessionAcknowledgeMode(acknowledgeMode);
        return jmsTemplate;
    }

    @Bean("queueContainerFactory")
    public DefaultJmsListenerContainerFactory queueListener(ActiveMQConnectionFactory factory){
        DefaultJmsListenerContainerFactory listener = new DefaultJmsListenerContainerFactory();
        listener.setConnectionFactory(factory);
        // 设置连接数  
        listener.setConcurrency("1-10");
        // 重连间隔时间  
        listener.setRecoveryInterval(1000L);
        listener.setSessionAcknowledgeMode(acknowledgeMode);
        return listener;
    }


    /**
     * 默认情况下，activemq只会监听queue队列消息，需要手动设置
     * @param factory
     * @return
     */
    @Bean("topicContainerFactory")
    public DefaultJmsListenerContainerFactory topicListener(ActiveMQConnectionFactory factory){
        DefaultJmsListenerContainerFactory listener = new DefaultJmsListenerContainerFactory();
        listener.setConnectionFactory(factory);
        // 设置连接数  
        listener.setConcurrency("1-10");
        // 重连间隔时间  
        listener.setRecoveryInterval(1000L);
        listener.setSessionAcknowledgeMode(JmsProperties.AcknowledgeMode.CLIENT.getMode());
        // 监听topic消息的factory,需要在@JmsListener中指定
        listener.setPubSubDomain(true);
        return listener;
    }
    
}
