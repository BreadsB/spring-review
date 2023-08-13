package com.breadsb.school.education.services;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import com.breadsb.school.education.repositories.TeacherRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;

    public List<Teacher> getTeachers() {
        return repository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return repository.findById(id);
    }

    public List<Teacher> getTeachersTeachingSubject(SchoolSubject ss) {
        return repository.findAllBySchoolSubject(ss);
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

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }

    public void updateTeacherById(Teacher teacher, Long id) {
        Teacher t = repository.findById(id).orElseThrow(EntityExistsException::new);
        t.setFirstName(teacher.getFirstName());
        t.setLastName(teacher.getLastName());
        t.setSchoolSubject(teacher.getSchoolSubject());
        t.setSchools(teacher.getSchools());
        repository.save(t);
    }
}
