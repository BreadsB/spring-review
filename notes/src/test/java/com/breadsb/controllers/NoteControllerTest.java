package com.breadsb.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private NoteController controller;

    @Autowired
    private MockMvc mvc;

    @Test
    void testController() {
        Assertions.assertNotNull(controller);
    }

    @Test
    void testNoteNotFound() throws Exception {
        this.mvc.perform(get("/notes/100")).andExpect(status().isNotFound());
    }

    @Test
    void testNoteFound() {

    }

    @Test
    void testNoteUpdated() {

    }
}