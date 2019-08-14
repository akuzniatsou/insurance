package com.dww.insurance.repository;

import com.dww.insurance.util.AppProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private static Properties props = AppProperties.getInstance().getAppProps();
    private static int INITIAL_POOL_SIZE = 10;
    private static ConnectionPool ourInstance = new ConnectionPool();
    private List<Connection> connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
    private List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                connectionPool.add(createConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
            props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
    }

    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

}
