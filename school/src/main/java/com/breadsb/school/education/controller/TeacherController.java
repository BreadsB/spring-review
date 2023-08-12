package com.breadsb.school.education.controller;

import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teachers")
class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping(produces = "application/json")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTeacher(Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping
    public void updateTeacher(Teacher teacher, Long id) {
        teacherService.updateTeacherById(teacher, id);
    }

    @PostMapping
    public void createTeacher(Teacher teacher) {
        teacherService.saveTeacher(teacher);
    }
}
