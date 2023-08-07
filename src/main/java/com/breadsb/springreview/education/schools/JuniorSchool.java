package com.breadsb.springreview.education.schools;

import com.breadsb.springreview.education.students.Student;
import com.breadsb.springreview.education.teachers.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class JuniorSchool implements School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public List<Student> getStudentList() {
        return null;
    }

    @Override
    public List<Teacher> getTeacherList() {
        return null;
    }
}
