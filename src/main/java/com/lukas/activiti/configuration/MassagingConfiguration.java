package com.lukas.activiti.configuration;

import com.lukas.activiti.loanapplication.messaging.DownpaymentsListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.*;

@Configuration
public class MassagingConfiguration {

    @Bean
    public ConnectionFactory activeMQConnectionFactory(@Value("${activemq.brokerUrl}") String brokerUrl) {
        RedeliveryPolicy topicPolicy = new RedeliveryPolicy();
        topicPolicy.setInitialRedeliveryDelay(3000);
        topicPolicy.setRedeliveryDelay(3000);
        topicPolicy.setUseExponentialBackOff(false);

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(topicPolicy);

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        jmsTemplate.setReceiveTimeout(1000);
        jmsTemplate.setDeliveryPersistent(true);
        return jmsTemplate;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public DefaultMessageListenerContainer aggregateStateChangedContainer(ConnectionFactory activeMQConnectionFactory,
                                                                          @Value("${activemq.topic.payments}") String aggregateStateChangedTopic,
                                                                          @Value("${spring.application.name}") String clientId,
                                                                          DownpaymentsListener downpaymentsListener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(activeMQConnectionFactory);
        container.setClientId(String.format("%s %s", "a", aggregateStateChangedTopic));
        container.setDestination(new ActiveMQTopic(aggregateStateChangedTopic));
        container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        container.setSessionTransacted(true);
        container.setSubscriptionDurable(true);
        container.setDurableSubscriptionName(String.format("%s %s", clientId, aggregateStateChangedTopic));
        container.setupMessageListener(downpaymentsListener);
        container.initialize();
        return container;
    }
}
