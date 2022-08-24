package com.chingiz.activemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfiguration {

    @Value("${activemq.broker.url}")
    private String brokerUrl;

    @Value("${activemq.username}")
    private String activemqUsername;

    @Value("${activemq.password}")
    private String activemqPassword;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(activemqUsername);
        connectionFactory.setPassword(activemqPassword);
        return connectionFactory;
    }

    @Bean
    @Qualifier("queueJmsTemplate")
    public JmsTemplate jmsQueueTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    @Qualifier("topicJmsTemplate")
    public JmsTemplate topicQueueTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setPubSubDomain(true);
        return template;
    }

    @Bean(name = "queueListenerFactory")
    public DefaultJmsListenerContainerFactory queueListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean(name = "topicListenerFactory")
    public DefaultJmsListenerContainerFactory topicListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        /**
            Making  subscription durable to make sure broker delivers message to subscribers
         */
        factory.setSubscriptionDurable(true);

        return factory;
    }

    @Bean(name = "virtualTopicListenerFactory")
    public DefaultJmsListenerContainerFactory virtualTopicListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        factory.setClientId("VirtualTopicConsumers");

        return factory;
    }



}
