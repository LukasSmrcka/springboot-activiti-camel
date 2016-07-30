package com.lukas.activiti.communication;

import com.google.gson.Gson;
import com.lukas.activiti.domain.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EntityStatePublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private Gson gson;

    public void publishAfterTransactionCommit(Aggregate aggregate) {
        String aggregateAsString = gson.toJson(aggregate);
        publisher.publishEvent(aggregateAsString);
    }
}
