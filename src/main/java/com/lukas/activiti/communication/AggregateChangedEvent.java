package com.lukas.activiti.communication;


public class AggregateChangedEvent {

    long aggregateId;
    String aggregateType;

    public long getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(long aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }
}
