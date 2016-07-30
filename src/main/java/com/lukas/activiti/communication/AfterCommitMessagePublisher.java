package com.lukas.activiti.communication;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AfterCommitMessagePublisher {

    private final JmsTemplate jmsTemplate;

    private final String notifyTopicName;

    @Autowired
    public AfterCommitMessagePublisher(@Value("${activemq.topic.notify}") String notifyTopicName,
                                       JmsTemplate jmsTemplate) {
        this.notifyTopicName = notifyTopicName;
        this.jmsTemplate = jmsTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishMessage(String aggregateAsString) {
        System.out.println("---------------");
        System.out.println("Publishing message after commit");
        System.out.println(aggregateAsString);

        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.send(new ActiveMQTopic(notifyTopicName), session -> session.createTextMessage(aggregateAsString));

        System.out.println("---------------");
    }
}
