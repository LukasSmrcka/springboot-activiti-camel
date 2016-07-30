package com.lukas.activiti.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

@Configuration
public class MassagingConfiguration {

    @Bean
    public ConnectionFactory activeMQConnectionFactory(@Value("${activemq.brokerUrl}") String brokerUrl) {
        RedeliveryPolicy topicPolicy = new RedeliveryPolicy();
        topicPolicy.setInitialRedeliveryDelay(0);
        topicPolicy.setRedeliveryDelay(1000);
        topicPolicy.setUseExponentialBackOff(false);
        topicPolicy.setMaximumRedeliveries(100);

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(topicPolicy);


        return activeMQConnectionFactory;
    }

    @Bean
    @Scope("prototype")
    public MessageProducer messageProducer(ConnectionFactory activeMQConnectionFactory,
                                           @Value("${activemq.topic.notify}") String notifyTopicName) throws JMSException {


            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

            //add to request scope
            Topic topic = session.createTopic(notifyTopicName);
            MessageProducer producer = session.createProducer(topic);
            return producer;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        jmsTemplate.setReceiveTimeout(1000);
        jmsTemplate.setDeliveryPersistent(true);
        return jmsTemplate;
    }
}
