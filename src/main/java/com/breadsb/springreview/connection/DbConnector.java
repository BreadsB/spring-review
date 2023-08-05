package com.breadsb.springreview.connection;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbConnector {

    INSTANCE;
    private final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;

    DbConnector() {
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", "postgres");
        connectionProperties.setProperty("password", "karpik199");
        connectionProperties.setProperty("ssl", "false");
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, connectionProperties);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DbConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
