package com.chingiz.activemqdemo.virtualtopic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class VirtualTopicListener2 {

    @JmsListener(destination = "VirtualTopicConsumers.consumer2.VirtualTopic.topic-1", containerFactory = "virtualTopicListenerFactory")
    public void receive(Message message) {
        System.out.println("Virtual topic consumer 2, message: " + message);
    }

}
