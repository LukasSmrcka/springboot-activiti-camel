package io.fourfinanceit.activiti.listeners;

import io.fourfinanceit.activiti.domain.CustomerCreatedEvent;
import org.springframework.context.ApplicationEvent;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class TransactionListener {


    PlatformTransactionManager transactionManager;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void listenBeforeCommit(CustomerCreatedEvent event) {
        System.out.println("---------------");
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            System.out.println("Transaction active");




        }
        System.out.println("BEFORE_COMMIT");
        System.out.println("Received: " + event.getName());
        System.out.println("---------------");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenAfterCommit(CustomerCreatedEvent event) {
        System.out.println("---------------");
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            System.out.println("Transaction active");

        }
        System.out.println("AFTER_COMMIT");
        System.out.println("Received: " + event.getName());
        System.out.println("---------------");
    }


}
