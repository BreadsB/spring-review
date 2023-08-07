package com.breadsb.education.schools;

import com.breadsb.education.students.Student;
import com.breadsb.education.teachers.Teacher;

import java.util.List;

public interface School {
    String getName();
    String getAddress();
    List<Student> getStudentList();
    List<Teacher> getTeacherList();
}
