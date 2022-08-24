package com.chingiz.activemqdemo.requestreply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
public class Producer {

    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate queueJmsTemplate;

    @Value("${activemq.queue}")
    private String queue;

    /**
     * Sends message to a queue
     * and waits for the answer from the temporary queue
     *
     * @param message
     * @return
     * @throws JMSException
     */
    public Message sendMessage(Message message) throws JMSException {

        queueJmsTemplate.setReceiveTimeout(1000L);

        Session session = queueJmsTemplate.getConnectionFactory().createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);

        message.setJMSCorrelationID(UUID.randomUUID().toString());
        message.setJMSReplyTo(session.createTemporaryQueue());
        message.setJMSExpiration(1000L);
        message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

        queueJmsTemplate.convertAndSend(queue, message);
        return queueJmsTemplate.receive(message.getJMSReplyTo());
    }

}
