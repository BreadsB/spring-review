package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
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
    void test() throws Exception {
        Mockito.when(service.getNoteById(note.getId())).thenReturn(note);
        this.mvc.perform(MockMvcRequestBuilders.get("/api/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}