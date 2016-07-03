package io.fourfinanceit.activiti.domain;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SimpleMessageProcessor {

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void processMessage() {
        System.out.println("SimpleMessageProcessor executed!!!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!!!");
    }
}
