package com.breadsb.school.education.services;

import com.breadsb.school.education.Student;
import com.breadsb.school.education.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Student getStudent(Long id) {
        return repository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public void saveStudent(Student s) {
        repository.save(s);
    }

    public void removeStudent(Long id) {
        repository.deleteById(id);
    }

    public void updateStudent(Student s, Long id) {
        Student student = repository.findById(id).orElseThrow(EntityExistsException::new);
        student.setFirstName(s.getFirstName());
        student.setLastName(s.getLastName());
        student.setBirthDate(s.getBirthDate());
        student.setSchool(s.getSchool());
        repository.save(student);
    }
}
