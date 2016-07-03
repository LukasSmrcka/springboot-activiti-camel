package io.fourfinanceit.activiti.configuration;


import io.fourfinanceit.activiti.domain.SimpleMessageProcessor;
import io.fourfinanceit.activiti.infrastructure.ThreadLocalConnectionsDatasource;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.apache.activemq.util.TransactionTemplate;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.jms.ConsumerType;
import org.apache.camel.component.jms.JmsConfiguration;

import org.apache.camel.spi.Policy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class CamelConfig extends CamelConfiguration {
    // http://camel.apache.org/how-do-i-make-my-jms-endpoint-transactional.html
    // http://camel.apache.org/transactional-client.html
    // About ActiveMQComponent http://camel.apache.org/activemq.html
    // http://xpadro.blogspot.cz/2013/08/spring-jms-processing-messages-within.html
    // https://www.javacodegeeks.com/2014/02/spring-jms-processing-messages-within-transactions.html
    // https://www.atomikos.com/Publications/ReliableJmsWithTransactions
    // http://hawt.io/

    //http://www.javaworld.com/article/2077963/open-source-tools/distributed-transactions-in-spring--with-and-without-xa.html
    //https://developer.jboss.org/thread/201789?tstart=0&_sscc=t

    //Zkusit s JPA transaction managerem,
    //Napsat threadlocal proxy pro Connection
    //Spring transaction manual
    //Data cosi Util clasa z manualu

    //Adapter izolation level


    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService brokerService(DataSource dataSource) throws Exception {
        BrokerService broker = new BrokerService();
        WireFormat wireFormat = new OpenWireFormat();


        //JmsTransactionAwareDataSourceProxy
        JDBCPersistenceAdapter persistenceAdapter = new JDBCPersistenceAdapter(dataSource, wireFormat);


        broker.setPersistenceAdapter(persistenceAdapter);
        broker.setPersistent(true);
        broker.setBrokerName("injvm");
        TransportConnector injvm = broker.getConnectorByName("injvm");
        //broker.addConnector("tcp://localhost:61616");
        TransportConnector transportConnector = broker.addConnector("vm://injvm");
        //transportConnector.
        URI vmConnectorURI = broker.getVmConnectorURI();


        transportConnector.setDisableAsyncDispatch(true);

        injvm = broker.getConnectorByName("injvm");
        //broker.setTransportConnectors();
        //TransportConnector transportConnector;
        return broker;
    }


    @Bean
    public ConnectionFactory jmsConnectionFactory() {



        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        //activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setBrokerURL("vm://injvm");
        //activeMQConnectionFactory.setDispatchAsync(false);

        //activeMQConnectionFactory.createQueueConnection().createQueueSession(true,0).createProducer(null).;
        return activeMQConnectionFactory;
    }

    //@Bean(initMethod = "start", destroyMethod = "stop")
    @Bean
    public JmsConfiguration jmsConfiguration(PlatformTransactionManager transactionManager) {

        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setTransacted(true);
        jmsConfiguration.setDeliveryPersistent(true);
        jmsConfiguration.setConnectionFactory(jmsConnectionFactory());
        jmsConfiguration.setTransactionManager(transactionManager);
        //jmsConfiguration.setLazyCreateTransactionManager(true);
       // jmsConfiguration.set

        return jmsConfiguration;
    }

    @Bean
    public ActiveMQComponent activeMQComponent(JmsConfiguration jmsConfiguration) throws Exception {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        //activeMQComponent.start();

        return activeMQComponent;
    }

    @Bean
    RoutesBuilder routesBuilder() {
        return new SpringRouteBuilder() {

            @Override
            public void configure() throws Exception {

                //Policy requried = new SpringTransactionPolicy(transactionManager);//bean(SpringTransactionPolicy.class, "PROPAGATION_REQUIRED");

                //from("jms:queue:TestQueueIn").to("jms:queue:TestQueueOut");



               // from("activemq:queue:TestQueueIn").transacted().bean(SimpleMessageProcessor.class, "processMessage");
            }

        };
    }
}
