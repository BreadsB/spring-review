package com.breadsb.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DbConnectionTest {

    @Test
    void testOne() {
        DbConnection dbConnection = DbConnection.getInstance();
        Assertions.assertNotNull(dbConnection);
    }
}