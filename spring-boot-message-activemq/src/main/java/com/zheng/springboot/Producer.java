package com.zheng.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;


/**
 * @Author Administrator
 * @Date 2018/5/21 10:20
 */
@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    
    public void sendMessage(Destination destination, Serializable obj) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message;
                if (obj instanceof String) {
                    message = session.createTextMessage((String) obj);
                } else {
                    message = session.createObjectMessage(obj);
                }
                return message;
            }
        });
    }
    
}
