package io.fourfinanceit.activiti.infrastructure;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Lukas on 23.6.2016.
 */
public class ThreadLocalConnectionsDatasource extends HikariDataSource {

    ThreadLocal<Connection> connectionThreadLocal;

    public ThreadLocalConnectionsDatasource() {
        super();
        connectionThreadLocal = new ThreadLocal<>();
    }


    // public Connection getConnection() throws SQLException {
    //     System.out.println("Getting new connection!!!");
    //     Connection connection = connectionThreadLocal.get();
    //     if(connection != null && connection.isClosed()) connection = null;
    //     if(connection == null) {
    //         System.out.println("Creating new connection!!!");
    //         connection = super.getConnection();
    //         connectionThreadLocal.set(connection);
    //     } else {
    //         System.out.println("Reusing existing connection!!!");
    //     }
    //     return connection;
    // }

    public Connection getConnection() throws SQLException {
        System.out.println("Getting new connection!!!");
        Connection connection;
        synchronized (this) {
            connection = connectionThreadLocal.get();

            if (connection != null && !connection.isClosed()) {
                System.out.println("Reusing existing connection!!!");
            } else {
                System.out.println("Creating new connection!!!");
                connection = super.getConnection();
                connectionThreadLocal.set(connection);
            }
        }
        return connection;
    }

    public Connection getConnection(String username, String password) throws SQLException {
        System.out.println("Getting new connection!!!");
        Connection connection;
        synchronized (this) {
            connection = connectionThreadLocal.get();

            if (connection != null && !connection.isClosed()) {
                System.out.println("Reusing existing connection!!!");
            } else {
                connectionThreadLocal.remove();
                System.out.println("Creating new connection!!!");
                connection = super.getConnection(username, password);
                connectionThreadLocal.set(connection);
            }
        }
        return connection;
    }

    // @Override
    // public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    //     return Logger.getAnonymousLogger();
    // }

    //@Override
    //public PrintWriter getLogWriter() throws SQLException {
    //    return wrappedDatasource.getLogWriter();
    //}
//
    //@Override
    //public void setLogWriter(PrintWriter out) throws SQLException {
    //    wrappedDatasource.setLogWriter(out);
    //}
//
    //@Override
    //public void setLoginTimeout(int seconds) throws SQLException {
    //    wrappedDatasource.setLoginTimeout(seconds);
    //}
//
    //@Override
    //public int getLoginTimeout() throws SQLException {
    //    return wrappedDatasource.getLoginTimeout();
    //}
//
    //@Override
    //public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    //    return wrappedDatasource.getParentLogger();
    //}
//
    //@Override
    //public <T> T unwrap(Class<T> iface) throws SQLException {
    //    return wrappedDatasource.unwrap(iface);
    //}
//
    //@Override
    //public boolean isWrapperFor(Class<?> iface) throws SQLException {
    //    return wrappedDatasource.isWrapperFor(iface);
    //}
//
    //public void close() throws SQLException {
    //    ((BasicDataSource)wrappedDatasource).close();
    //}
}
