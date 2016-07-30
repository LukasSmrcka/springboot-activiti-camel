package com.lukas.activiti.communication;

import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AggregateChangedTransactionListener {

    private final JmsTemplate jmsTemplate;

    private final String notifyTopicName;

    private final Gson gson;

    @Autowired
    public AggregateChangedTransactionListener(@Value("${activemq.topic.notify}") String notifyTopicName,
                                               JmsTemplate jmsTemplate,
                                               Gson gson) {
        this.notifyTopicName = notifyTopicName;
        this.jmsTemplate = jmsTemplate;
        this.gson = gson;
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterAggregateCommit(AggregateChangedEvent event) {
        System.out.println("---------------");
        System.out.println("Publishing Aggregate changed event after commit");
        System.out.println("Type: " + event.getAggregateType());
        System.out.println("Id: " + event.getAggregateId());
        String eventString = gson.toJson(event);
        jmsTemplate.send(new ActiveMQTopic(notifyTopicName), session -> session.createTextMessage(eventString));
        System.out.println("---------------");
    }
}
