package com.breadsb.school.education.controller;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.services.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private MockMvc mvc;

    @MockBean
    TeacherService service;

    @Test
    void testLoadController() {
        Assertions.assertNotNull(teacherController);
    }

    @Test
    void givenTeacher_whenGetTeacher_thenReturnJson() throws Exception {
        Teacher teacher = new Teacher("first", "second", SchoolSubject.MATH);
        long id = 1L;
        given(service.getTeacherById(1L)).willReturn(teacher);
        mvc.perform(get("/teachers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(teacher.getFirstName())));
    }
}