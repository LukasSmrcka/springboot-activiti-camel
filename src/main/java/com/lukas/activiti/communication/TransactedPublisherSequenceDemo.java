package com.lukas.activiti.communication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class TransactedPublisherSequenceDemo {

    @Autowired
    private ConnectionFactory activeMQConnectionFactory;

    @Value("${activemq.topic.notify}")
    private String notifyTopicName;

    public void publishTransactedDemoMessage() {

        Connection connection;
        try {
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            //2nd param Session.SESSION_TRANSACTED is ignored when the first(transacted) is true
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            //add to request scope ore find JMS 2.0 solution
            Topic topic = session.createTopic(notifyTopicName);
            TextMessage textMessage = session.createTextMessage("Hello, I am message");
            MessageProducer producer = session.createProducer(topic);

            producer.send(textMessage);

            session.commit();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
