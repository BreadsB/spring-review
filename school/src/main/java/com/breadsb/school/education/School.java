package com.breadsb.school.education;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class School {

    private long id;
    private String name;
    private String city;
    private String address;
    private List<Student> students;
    private List<Teacher> teachers;

    public School(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
