package com.breadsb.school.education.controller;

import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.services.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teachers")
class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(produces = "application/json")
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Teacher getTeacher(@PathVariable Long id) {
        return teacherService.getTeacherById(id).orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping
    public void updateTeacher(Teacher teacher, Long id) {
        teacherService.updateTeacherById(teacher, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostResponse createTeacher(@RequestBody Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return new PostResponse("Teacher created"); // This will return body!
    }

    @GetMapping("/value")
    public String getSomeValue(@RequestParam String b1) {
        return "This is my value: " + b1;
    }
}