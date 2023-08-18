package com.breadsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum NotesDbConnector {

    INSTANCE;
    private Connection connection;
    private final String URL = "jdbc:postgresql://localhost:5432/notes";

    NotesDbConnector() {
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "karpik199");
        properties.put("ssl", "false");
        try {
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static NotesDbConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
