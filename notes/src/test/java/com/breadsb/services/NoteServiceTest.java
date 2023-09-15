package com.breadsb.services;

import com.breadsb.entities.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteServiceTest {

    @Autowired
    NoteService service;

    private Note note;

    @BeforeEach
    void setUpBeforeEach() {
        note = new Note("Test title", "Test body");
    }

    @Test
    void saveNewNoteToRepository() {
        service.createNote(note);
        long id = note.getId();

        Assertions.assertEquals(1L, id);
    }
}