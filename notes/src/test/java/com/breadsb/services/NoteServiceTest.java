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
import java.util.ArrayList;
import java.util.List;
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
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(note));
        Assertions.assertThat(service.getNoteById(note.getId())).isEqualTo(note);
    }

    @Test
    void testDeleteNote() {
        service.deleteNoteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateNote() {
        Note updatedNote = new Note("updatedTitle", "updatedBody");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(note));
        service.updateNote(1L, updatedNote);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Note.class));
    }

    @Test
    void testGetAllNotes() {
        List<Note> list = new ArrayList<>();
        list.add(new Note());
        Mockito.when(repository.findAll()).thenReturn(list);
        List<Note> retList = service.getAllNotes();
        Assertions.assertThat(retList.size()).isEqualTo(1);
        verify(repository, times(1)).findAll();
    }
}