package com.chingiz.activemqdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TopicListener {

    @Value("${activemq.topic}")
    private String topic;

    /*
        Creating 2 listeners to implement Publish and Subscribe model.
     */
    @JmsListener(destination = "${activemq.queue}", containerFactory = "topicListenerFactory")
    public void receiveMessageFromTopicListener1(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("Queue: " + topic + ", listener-1, message: " + text);
    }

    @JmsListener(destination = "${activemq.queue}", containerFactory = "topicListenerFactory")
    public void receiveMessageFromTopicListener2(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("Queue: " + topic + ", listener-2, message: " + text);
    }

}
