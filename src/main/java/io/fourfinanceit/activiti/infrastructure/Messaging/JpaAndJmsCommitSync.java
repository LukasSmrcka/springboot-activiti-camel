package io.fourfinanceit.activiti.infrastructure.Messaging;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class JpaAndJmsCommitSync extends TransactionSynchronizationAdapter {

    @Override
    public void beforeCompletion() {
        super.beforeCompletion();

    }

    public void register() {
        TransactionSynchronizationManager.registerSynchronization(this);
    }
}
