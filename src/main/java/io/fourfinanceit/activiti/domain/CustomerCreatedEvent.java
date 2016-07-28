package io.fourfinanceit.activiti.domain;

public class CustomerCreatedEvent {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
