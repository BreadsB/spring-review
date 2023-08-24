package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes/")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class NoteController {

    private final NoteService service;

    @GetMapping("{id}")
    public ResponseEntity<Note> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNoteById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> post(@RequestBody Note note) {
        service.createNote(note);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> put(@PathVariable Long id, @RequestBody Note note) {
        service.updateNote(id, note);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteNoteById(id);
        return ResponseEntity.ok().build();
    }
}