package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notes")
@RequiredArgsConstructor
public class NoteController {

    private NoteService service;

    @GetMapping("{id}")
    public Note getNote(@PathVariable Long id) {
        return service.getNote(id);
    }
}