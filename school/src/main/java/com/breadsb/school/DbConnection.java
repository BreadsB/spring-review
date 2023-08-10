package com.breadsb.school;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum DbConnection {

    INSTANCE;
    private final String URL = "jdbc:postgresql://localhost:5432/schools";
    private Connection connection;

    DbConnection() {
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", getPassword());
        try {
            connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() {
        return INSTANCE;
    }

    private String getPassword() {
        try {
            InputStream is = new FileInputStream("E:\\IntelliJ_Projects\\e78b32.properties");
            Properties properties = new Properties();
            properties.load(is);
            char[] pass = properties.getProperty("postgresqldb.password").toCharArray();
            is.close();
            properties.clear();
            return String.valueOf(pass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}