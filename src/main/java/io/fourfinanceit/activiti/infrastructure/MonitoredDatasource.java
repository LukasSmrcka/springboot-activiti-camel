package io.fourfinanceit.activiti.infrastructure;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class MonitoredDatasource extends BasicDataSource {


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

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return Logger.getAnonymousLogger();
    }
}
