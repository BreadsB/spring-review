package com.breadsb.exceptions;

import com.breadsb.controllers.NoteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomExceptionsTesting {

    @Autowired
    private NoteController controller;

    @Autowired
    private MockMvc mvc;

    @Test
    void testThrowMessageBodyTooLongException() throws Exception {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nulla nec nulla id neque feugiat efficitur. " +
                "Sed at ultrices metus. " +
                "Proin a quam et orci consectetur iaculis. " +
                "Vestibulum et velit at justo congue mattis. " +
                "Sed sit amet libero at nisl faucibus gravida.";

        this.mvc.perform(post("/api/notes/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s)).andExpect(status().isBadRequest());
    }
}
