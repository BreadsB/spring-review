package com.breadsb.springreview.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbConnectorTest {

    @Test
    void testConnection() {
        DbConnector dbConnector = DbConnector.getInstance();
        Assertions.assertNotNull(dbConnector);
    }
}
