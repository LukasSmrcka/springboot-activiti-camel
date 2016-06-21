package io.fourfinanceit.activiti.configuration;


import io.fourfinanceit.activiti.domain.SimpleMessageProcessor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class CamelConfig extends CamelConfiguration {
    public static final String HELLO_QUEUE = "jms:queue:TestQueueOut";
    // http://camel.apache.org/how-do-i-make-my-jms-endpoint-transactional.html
    // http://camel.apache.org/transactional-client.html
    // About ActiveMQComponent http://camel.apache.org/activemq.html
    // http://xpadro.blogspot.cz/2013/08/spring-jms-processing-messages-within.html
    // https://www.javacodegeeks.com/2014/02/spring-jms-processing-messages-within-transactions.html
    // https://www.atomikos.com/Publications/ReliableJmsWithTransactions
    // http://hawt.io/

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }

    //@Bean(initMethod = "start", destroyMethod = "stop")
    @Bean
    public JmsConfiguration jmsConfiguration() {

        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setTransacted(true);
        jmsConfiguration.setDeliveryPersistent(true);
        jmsConfiguration.setConnectionFactory(jmsConnectionFactory());
        return jmsConfiguration;
    }

    @Bean
    public ActiveMQComponent activeMQComponent() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        //activeMQComponent.setTransactionManager();
        activeMQComponent.setConfiguration(jmsConfiguration());
        return activeMQComponent;
    }


    @Bean
    RoutesBuilder myRouter() {
        return new SpringRouteBuilder() {

            @Override
            public void configure() throws Exception {
                //from("jms:queue:TestQueueIn").to("jms:queue:TestQueueOut");

                

                from("activemq:queue:TestQueueIn").transacted().bean(SimpleMessageProcessor.class,"processMessage");
            }

        };
    }
}
