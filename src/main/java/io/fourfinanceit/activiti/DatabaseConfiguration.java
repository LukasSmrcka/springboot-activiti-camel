package io.fourfinanceit.activiti;

import com.zaxxer.hikari.HikariDataSource;
import io.fourfinanceit.activiti.infrastructure.MonitoredDatasource;
import io.fourfinanceit.activiti.infrastructure.SharedResourceDatasourceProxy;
import io.fourfinanceit.activiti.infrastructure.ThreadLocalConnectionsDatasource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Autowired
    private DataSourceProperties properties;

  //  @Bean(destroyMethod = "close")
  //  @ConfigurationProperties(DataSourceProperties.PREFIX)
  //  public DataSource dataSource() {
  //      ThreadLocalConnectionsDatasource dataSource = (ThreadLocalConnectionsDatasource) DataSourceBuilder
  //              .create(this.properties.getClassLoader())
  //              .type(ThreadLocalConnectionsDatasource.class)
  //              .driverClassName(this.properties.getDriverClassName())
  //              .url(this.properties.getUrl())
  //              .username(this.properties.getUsername())
  //              .password(this.properties.getPassword())
  //              .build();
//
//
  //      return dataSource;
  //  }

   // @Bean
   // @ConfigurationProperties(DataSourceProperties.PREFIX)
   // public DataSource dataSource() {
   //     HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
   //             .create(this.properties.getClassLoader())
   //             .type(HikariDataSource.class)
   //             .driverClassName(this.properties.getDriverClassName())
   //             .url(this.properties.getUrl())
   //             .username(this.properties.getUsername())
   //             .password(this.properties.getPassword())
   //             .build();
   //     TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
//
   //     return dataSourceProxy;
   // }

    @Bean
    @ConfigurationProperties(DataSourceProperties.PREFIX)
    public DataSource dataSource() {
        BasicDataSource dataSource = new MonitoredDatasource();
        dataSource.setDriverClassName(this.properties.getDriverClassName());
        dataSource.setUrl(this.properties.getUrl());
        dataSource.setUsername(this.properties.getUsername());
        dataSource.setPassword(this.properties.getPassword());
        dataSource.setAccessToUnderlyingConnectionAllowed(true);



        //dataSourceProxy.setReobtainTransactionalConnections();
        //ThreadLocalConnectionsDatasource proxyDataSource = new ThreadLocalConnectionsDatasource(dataSource);
        return dataSource;
    }

    //@Bean(destroyMethod = "close")
    //@ConfigurationProperties(DataSourceProperties.PREFIX)
    //public DataSource dataSource() {
    //    ThreadLocalConnectionsDatasource dataSource = new ThreadLocalConnectionsDatasource();
    //    //BasicDataSource dataSource = new BasicDataSource();
    //    dataSource.setDriverClassName(this.properties.getDriverClassName());
    //    dataSource.setUrl(this.properties.getUrl());
    //    dataSource.setUsername(this.properties.getUsername());
    //    dataSource.setPassword(this.properties.getPassword());
    //    //ThreadLocalConnectionsDatasource proxyDataSource = new ThreadLocalConnectionsDatasource(dataSource);
    //    return dataSource;
    //}

   // @Bean
   // public PlatformTransactionManager transactionManager(DataSource dataSource) {
   //     PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
   //     return transactionManager;
   // }

}
