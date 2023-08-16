package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @GetMapping("{id}")
    public Note getNoteById(@PathVariable Long id) {
        return service.getNoteById(id);
    }

    @PostMapping
    public void saveNote(@RequestBody Note note) {
        service.saveNote(note);
    }
}