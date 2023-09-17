package com.breadsb.controllers;

import com.breadsb.entities.Note;
import com.breadsb.exceptions.ResourceNotFoundException;
import com.breadsb.services.NoteService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        long id = 1L;
        String note = "{\"id\": " + id +
                ", \"title\": \"sometitle\", \"body\": \"somebody\"}";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/notes/" + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(note);

        MvcResult result = this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}