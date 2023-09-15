package com.breadsb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotesDbConnectorTest {

    @Test
    void testConnection() {
        NotesDbConnector connector = NotesDbConnector.getInstance();
        Assertions.assertNotNull(connector.getConnection());
    }
}