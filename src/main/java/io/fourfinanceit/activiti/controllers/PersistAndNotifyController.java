package io.fourfinanceit.activiti.controllers;


import io.fourfinanceit.activiti.domain.Customer;
import io.fourfinanceit.activiti.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Scope("request")
public class PersistAndNotifyController {

   //@Autowired
   //private ProducerTemplate producerTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/send")
    @Transactional
    public String send() {


        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Rambo");
        customerRepository.save(customer);


        System.out.println("Sending");

        return "Message send";
    }
}
