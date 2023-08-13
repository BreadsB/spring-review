package com.breadsb.school.education.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @Test
    void testLoadController() {
        Assertions.assertNotNull(teacherController);
    }
}
