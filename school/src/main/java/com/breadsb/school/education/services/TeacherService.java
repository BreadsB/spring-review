package com.breadsb.school.education.services;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repository;

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

    public void saveTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public List<Teacher> getTeachersTeachingSubjectAndTeachingInNumberOfSchools(SchoolSubject ss, int number) {
        return repository.retrieveTeachersWithSubjectAndSchoolsGreaterThen(ss,number);
    }

    public void saveTeacherCollection(Collection<Teacher> teachers) {
        repository.saveAll(teachers);
    }
}
