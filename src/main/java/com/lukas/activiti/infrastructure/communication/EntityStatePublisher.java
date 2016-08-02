package com.lukas.activiti.infrastructure.communication;

import com.google.gson.Gson;
import com.lukas.activiti.infrastructure.base.domain.AggregateRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EntityStatePublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private Gson gson;

    public void publishAfterTransactionCommit(AggregateRoot aggregate) {
        String aggregateAsString = gson.toJson(aggregate);
        publisher.publishEvent(aggregateAsString);
    }
}
