package com.chingiz.activemqdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class QueueListener {

    @Value("${activemq.queue}")
    private String queue;

    @JmsListener(destination = "${activemq.queue}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromQueue(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("Queue: " + queue + ", message: " + text);

    }
}
