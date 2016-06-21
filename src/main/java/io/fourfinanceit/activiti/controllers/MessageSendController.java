package io.fourfinanceit.activiti.controllers;


import org.activiti.engine.RuntimeService;
import org.apache.camel.Endpoint;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.component.jms.JmsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("request")
public class MessageSendController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @RequestMapping("/send")
    public String send() {


        JmsConfiguration jmsConfiguration =  new JmsConfiguration();
        jmsConfiguration.setTransacted(true);
        Endpoint endpoint = new JmsEndpoint("jms://queue:TestQueueIn", "TestQueueIn",false );
        //http://camel.apache.org/how-do-i-make-my-jms-endpoint-transactional.html
        //http://camel.apache.org/transactional-client.html
        producerTemplate.sendBody("jms://queue:TestQueueIn", ExchangePattern.RobustInOnly,"Some body");

        return "Message send";
    }
}
