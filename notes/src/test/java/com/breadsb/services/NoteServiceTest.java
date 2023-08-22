package com.breadsb.services;

import com.breadsb.entities.Note;
import com.breadsb.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class NoteServiceTest {

    @Autowired
    NoteService service;

    Note note;

    @Test
    void givenNote_whenGetById_thenReceiveNote() {
        //given
        note = new Note("Test title1", "Test body1");
        //when
        service.createNote(note);
        //then
        Long id = note.getId();
        Note receivedNote = service.getNoteById(id);
        Assertions.assertNotNull(receivedNote);
        Assertions.assertEquals("Test title1", receivedNote.getTitle());
        Assertions.assertEquals(receivedNote.getCreatedAt().getDayOfYear(), LocalDateTime.now().getDayOfYear());
    }

    @BeforeEach
    void setUp() {
        note = new Note("My test note", "Something in body");
    }

    @Test
    void testDeleteNoteFromDb() {

        service.createNote(note);
        long id = note.getId();

        service.deleteNoteById(id);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Note receivedNote = service.getNoteById(id);
        });
    }

    @Test
    void testUpdateNote() {
        service.createNote(note);
        long id = note.getId();
        Note updateNote = new Note("Updated title", "Updated body");
        service.updateNote(id,updateNote);

        Note retrieve = service.getNoteById(id);
        Assertions.assertEquals("Updated title", retrieve.getTitle());
        Assertions.assertEquals("Updated body", retrieve.getBody());
    }
}