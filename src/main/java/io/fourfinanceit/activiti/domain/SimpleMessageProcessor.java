package io.fourfinanceit.activiti.domain;

import org.springframework.stereotype.Component;

@Component
public class SimpleMessageProcessor {

    public void processMessage() {
        System.out.println("SimpleMessageProcessor executed!!!");
    }
}
