package com.lukas.activiti.controllers;


import com.lukas.activiti.communication.EntityStatePublisher;
import com.lukas.activiti.domain.Customer;
import com.lukas.activiti.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.transaction.annotation.Transactional;

@RestController
@Scope("request")
public class PersistAndNotifyController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    EntityStatePublisher publisher;

    @RequestMapping("/persist-and-notify")
    @Transactional
    public String persistAndNotify() {


        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Rambo");
        customerRepository.save(customer);

        publisher.publishAfterTransactionCommit(customer);

        return "Persisted and notified";
    }
}
