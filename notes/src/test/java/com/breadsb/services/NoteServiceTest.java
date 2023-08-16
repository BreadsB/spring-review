package com.breadsb.services;

import com.breadsb.entities.Note;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class NoteServiceTest {

    @Autowired
    NoteService service;

    @Test
    void givenNote_whenGetById_thenReceiveNote() {
        Note note = new Note();
        note.setTitle("Test title");
        note.setBody("Test body");

        service.saveNote(note);
        Long id = note.getId();

        Note receivedNote = service.getNoteById(id);

        Assertions.assertNotNull(receivedNote);
        Assertions.assertEquals("Test title", receivedNote.getTitle());
    }
}