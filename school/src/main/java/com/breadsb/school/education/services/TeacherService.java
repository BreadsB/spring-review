package com.breadsb.school.education.services;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    TeacherRepository repository;

    @Autowired
    TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getTeachers() {
        return repository.findAll();
    }

    public List<Teacher> getTeachersTeachingSubject(SchoolSubject ss) {
        return repository.findBySchoolSubject(ss);
    }
}
