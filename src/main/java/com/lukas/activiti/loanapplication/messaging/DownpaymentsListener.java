package com.lukas.activiti.loanapplication.messaging;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class DownpaymentsListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("Downpayment received: " + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
