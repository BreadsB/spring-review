package com.breadsb.school;


import java.sql.Connection;

public enum DbConnection {

    INSTANCE;

    private final String URL = "jdbc:postgresql://localhost:5432/";
    private Connection connection;

    DbConnection() {

    }
}
