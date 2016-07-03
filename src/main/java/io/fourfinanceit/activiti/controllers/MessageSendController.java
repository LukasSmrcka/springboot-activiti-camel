package io.fourfinanceit.activiti.controllers;


import io.fourfinanceit.activiti.domain.Customer;
import io.fourfinanceit.activiti.domain.CustomerRepository;
import org.activiti.engine.RuntimeService;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.component.jms.JmsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Scope("request")
public class MessageSendController {

   //@Autowired
   //private ProducerTemplate producerTemplate;

    @Autowired
    CamelContext camelContext;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/send")
    @Transactional
    public String send() {

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();

        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Rambo");
        customerRepository.save(customer);
     // JmsConfiguration jmsConfiguration =  new JmsConfiguration();
     // jmsConfiguration.setTransacted(true);
     // Endpoint endpoint = new JmsEndpoint("jms://queue:TestQueueIn", "TestQueueIn",false );
     // //http://camel.apache.org/how-do-i-make-my-jms-endpoint-transactional.html
        //http://camel.apache.org/transactional-client.html

        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println("Thread Id " + Thread.currentThread().getId());
        System.out.println("Thread Name " + Thread.currentThread().getName());
        System.out.println("Sending");
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

        producerTemplate.sendBody("activemq:queue:TestQueueIn", "Some body");

       // if(producerTemplate != null) throw new NullPointerException();

        return "Message send";
    }
}
