package com.chingiz.activemqdemo.virtualtopic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class Producer {

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate topicJmsTemplate;

    public void sendToVirtualTopic(Message message) {
        topicJmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic.topic-1"), message);
    }

}
