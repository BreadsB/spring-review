package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.exceptions.ResourceNotFoundException;
import com.breadsb.services.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@WithMockUser
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
        note.setBody("mybody");
        note.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetNoteUsingRequestMvc() throws Exception {
        Mockito.when(service.getNoteById(note.getId())).thenReturn(note);
        this.mvc.perform(MockMvcRequestBuilders.get("/api/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("mybody"));
    }

    @Test
    void testGetNotExistingNoteAndThrowException() throws Exception {
        long id = 1L;
        Mockito.when(service.getNoteById(id)).thenThrow(ResourceNotFoundException.class);
        this.mvc.perform(MockMvcRequestBuilders.get("/api/notes/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Custom message"));
    }

    @Test
    void testUpdateNote() throws Exception {
        Mockito.when(service.updateNote(anyLong(), any(Note.class))).thenReturn(note);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Note note2 = new Note("title2", "body2");

        String noteToJson = objectMapper.writeValueAsString(note2);

//        String noteString = "{\"title\": \"sometitle\", \"body\": \"somebody\"}";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/notes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(noteToJson);

        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testPostNewNote() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Note note2 = new Note("title2", "body2");

        String noteToJson = objectMapper.writeValueAsString(note2);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/notes/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(noteToJson);
        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteNote() throws Exception {
        Mockito.doNothing().when(service).deleteNoteById(1L);
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/notes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllNotes() throws Exception {
        List<Note> list = new ArrayList<>();
        list.add(new Note());
        Mockito.when(service.getAllNotes()).thenReturn(list);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/notes/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}