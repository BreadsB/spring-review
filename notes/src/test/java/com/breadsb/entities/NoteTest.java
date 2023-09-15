package com.breadsb.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class NoteTest {

    @Test
    void testCreateNewNote_andSetFieldsValues() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("title");
        note.setBody("body");
        LocalDateTime date = LocalDateTime.now();
        note.setCreatedAt(date);

        Assertions.assertEquals(1L, note.getId());
        Assertions.assertEquals("title", note.getTitle());
        Assertions.assertEquals("body", note.getBody());
        Assertions.assertEquals(date, note.getCreatedAt());
    }
}