package com.chingiz.activemqdemo.requestreply;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Consumer implements SessionAwareMessageListener<Message> {

    /**
     * Receives message from main queue and sends the response to the temporary queue
     *
     * @param message
     * @param session
     * @throws JMSException
     */
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage response = new ActiveMQTextMessage();
        response.setText("Response");
        response.setJMSCorrelationID(message.getJMSCorrelationID());

        final MessageProducer producer = session.createProducer(message.getJMSReplyTo());
        producer.send(response);
    }
}
