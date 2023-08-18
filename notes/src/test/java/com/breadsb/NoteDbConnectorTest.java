package com.breadsb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteDbConnectorTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testPostgreSQLDbConnection_usingJdbcTemplate() {
        assertNotNull(jdbcTemplate);
    }

    @Test
    void testConnectionWithNotesDb_usingCustomDbConnector() {
        NotesDbConnector dbConnector = NotesDbConnector.getInstance();
        assertNotNull(dbConnector.getConnection());
    }
}
