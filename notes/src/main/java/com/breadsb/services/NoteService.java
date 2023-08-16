package com.breadsb.services;

import com.breadsb.entities.Note;
import com.breadsb.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public void saveNote(Note note) {
        repository.save(note);
    }

    public Note getNoteById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}