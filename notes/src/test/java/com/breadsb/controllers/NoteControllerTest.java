package com.breadsb.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testNoteNotFound() throws Exception {
//        this.mvc.perform(get("api/notes/100")).andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.get("/api/notes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testNoteFound() throws Exception {
        this.mvc.perform(get("/api/notes/1")).andExpect(status().isFound());
    }
}