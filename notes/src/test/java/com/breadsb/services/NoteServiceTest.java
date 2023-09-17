package com.breadsb.services;

import com.breadsb.entities.Note;
import com.breadsb.repositories.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository repository;

    @InjectMocks
    @Autowired
    private NoteService service;
    private Note note;

    @BeforeEach
    void init() {
        note = new Note();
        note.setId(1L);
        note.setTitle("title");
        note.setBody("body");
        note.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void contextLoad() {

    }

    @Test
    void saveNote() {
        when(repository.save(any())).thenReturn(note);
        service.createNote(note);
        verify(repository, times(1)).save(any());
    }

    @Test
    void testGetNote() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(note));
        Assertions.assertThat(service.getNoteById(note.getId())).isEqualTo(note);
    }

    @Test
    void testDeleteNote() {
        service.deleteNoteById(1L);
        verify(repository,times(1)).deleteById(1L);
    }
}