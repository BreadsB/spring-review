package com.breadsb.services;

import com.breadsb.entities.Note;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class NoteServiceTest {

    @Autowired
    NoteService service;

    @Test
    @Transactional
    void givenNote_whenGetById_thenReceiveNote() {
        //given
        Note note1 = new Note("Test title1", "Test body1");
        //when
        service.createNote(note1);
        //then
        Long id = note1.getId();
        Note receivedNote = service.getNoteById(id);
        Assertions.assertNotNull(receivedNote);
        Assertions.assertEquals("Test title1", receivedNote.getTitle());
        Assertions.assertEquals(receivedNote.getCreatedAt().getDayOfYear(), LocalDateTime.now().getDayOfYear());
    }
}