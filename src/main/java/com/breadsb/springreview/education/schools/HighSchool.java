package com.breadsb.springreview.education.schools;

import com.breadsb.springreview.education.students.Student;
import com.breadsb.springreview.education.teachers.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Entity
public class HighSchool implements School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Student.class,
            mappedBy = "school"
    )
    private List<Student> students;

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
