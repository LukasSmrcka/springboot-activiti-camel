package com.lukas.activiti.controllers;


import com.lukas.activiti.communication.AggregateChangedEvent;
import com.lukas.activiti.domain.CustomerRepository;
import com.lukas.activiti.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Scope("request")
public class PersistAndNotifyController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    ApplicationEventPublisher publisher;

    @RequestMapping("/persist-and-notify")
    @Transactional
    public String persistAndNotify() {


        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Rambo");
        customerRepository.save(customer);

        AggregateChangedEvent event = new AggregateChangedEvent();
        event.setAggregateId(customer.getId());
        event.setAggregateType(Customer.class.toString());

        publisher.publishEvent(event);

        return "Persisted and notified";
    }
}
