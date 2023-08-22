package com.breadsb.services;

import com.breadsb.entities.Note;
import com.breadsb.exceptions.ResourceNotFoundException;
import com.breadsb.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public void createNote(Note note) {
        repository.save(note);
    }

    public Note getNoteById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note not found"));
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public void deleteNoteById(Long id) {
        repository.deleteById(id);
    }

    public void updateNote(Long id, Note note) {
        Note repoNote = getNoteById(id);
        repoNote.setTitle(note.getTitle());
        repoNote.setBody(note.getBody());
        repoNote.setModifiedAt(LocalDateTime.now());
        repository.save(note);
    }
}