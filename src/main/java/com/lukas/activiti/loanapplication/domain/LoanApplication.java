package com.lukas.activiti.loanapplication.domain;

import com.lukas.activiti.infrastructure.base.domain.AggregateRoot;

import javax.persistence.Entity;

@Entity
public class LoanApplication extends AggregateRoot {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;
}
