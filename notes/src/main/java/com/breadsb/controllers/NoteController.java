package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notes/")
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> put(@PathVariable Long id, @RequestBody Note note) {
        service.updateNote(id, note);
        String uri = "http://localhost:8080/api/notes/" + id;
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteNoteById(id);
        return ResponseEntity.ok().build();
    }

//    Example of usage:
//    http://localhost:8080/api/notes/by-date/?timestamp=2023-09-11T00:00:00
//    Above will return list of notes that has been created at the 2023-09-11 (11th September)
    @GetMapping("/by-date/")
    public ResponseEntity<List<Note>> getNotesByDate(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        return ResponseEntity.ok(service.findByCreatedAt(timestamp));
    }
}