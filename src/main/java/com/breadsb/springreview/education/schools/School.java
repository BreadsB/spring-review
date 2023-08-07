package com.breadsb.springreview.education.schools;

import com.breadsb.springreview.education.students.Student;
import com.breadsb.springreview.education.teachers.Teacher;

import java.util.List;

public interface School {
    String getName();
    String getAddress();
    List<Student> getStudentList();
    List<Teacher> getTeacherList();
}
