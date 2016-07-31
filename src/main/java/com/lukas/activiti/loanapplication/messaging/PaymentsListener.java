package com.lukas.activiti.loanapplication.messaging;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class PaymentsListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        System.out.println("---------------");
        System.out.println("Message received");
        try {
            System.out.println("Payment received: " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("---------------");
    }
}
