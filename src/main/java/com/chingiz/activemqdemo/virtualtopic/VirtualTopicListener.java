package com.chingiz.activemqdemo.virtualtopic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class VirtualTopicListener {

    @JmsListener(destination = "VirtualTopicConsumers.consumer1.VirtualTopic.topic-1")
    public void receive(Message message) {
        System.out.println("Virtual topic consumer 1, message: " + message);
    }

}
