package io.fourfinanceit.activiti.infrastructure;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Lukas on 24.6.2016.
 */
public class SharedResourceDatasourceProxy extends TransactionAwareDataSourceProxy {
    public SharedResourceDatasourceProxy(DataSource targetDataSource) {
        super(targetDataSource);
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection =  super.getConnection();


        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println("Thread Id " + Thread.currentThread().getId());
        System.out.println("Thread Name " + Thread.currentThread().getName());
        System.out.println("Connection hash " + connection.hashCode());
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        return connection;
    }
}
