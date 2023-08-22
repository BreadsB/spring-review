package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NoteController {

    private final NoteService service;

    @GetMapping("/{id}")
    public Note get(@PathVariable Long id) {
        return service.getNoteById(id);
    }

    @PostMapping
    public void create(@RequestBody Note note) {
        service.createNote(note);
    }

    @GetMapping
    public List<Note> getAll() {
        return service.getAllNotes();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Note note) {
        service.updateNote(id, note);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.deleteNoteById(id);
    }
}