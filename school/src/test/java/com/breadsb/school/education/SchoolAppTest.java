package com.breadsb.school.education;

import com.breadsb.school.education.services.SchoolService;
import com.breadsb.school.education.services.TeacherService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class SchoolAppTest {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @Test
    void saveSchoolWithTeacherAndStudentAlong() {
        Student student = new Student("Mike", "Morrison", LocalDate.of(1990,1,7));
        School school = new School("Super hyper Junior School", "Washington", "17th Street a");
        Teacher teacher = new Teacher("Thomas", "Shelby", SchoolSubject.BIOLOGY);
        school.addTeacher(teacher);
        school.addStudent(student);

        schoolService.saveSchool(school);
        long id = school.getId();
        School retrievedSchool = schoolService.getSchool(id);

        Assertions.assertNotNull(retrievedSchool);
        Assertions.assertTrue(id > 0);
    }

    @Test
    void addTwoEntities() {
        Teacher teacher = new Teacher("Thomas", "Shelby", SchoolSubject.BIOLOGY);
        School school = new School("Super hyper Junior School", "Washington", "17th Street a");
        school.addTeacher(teacher);
        teacher.addSchool(school);

        schoolService.saveSchool(school);
        long id = school.getId();
        Assertions.assertTrue(id > 0);
    }

    @Test
    void testGetAllTeachers() {
        Teacher teacher1 = new Teacher("John", "Doe", SchoolSubject.PHYSICAL_EDUCATION);
        Teacher teacher2 = new Teacher("John", "Doe", SchoolSubject.PHYSICAL_EDUCATION);
        Teacher teacher3 = new Teacher("John", "Doe", SchoolSubject.PHYSICAL_EDUCATION);
        Teacher teacher4 = new Teacher("John", "Doe", SchoolSubject.PHYSICAL_EDUCATION);

        List<Teacher> teachers = new ArrayList<>();

        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);

        teacherService.saveTeacherCollection(teachers);

        teachers = teacherService.getTeachersTeachingSubject( SchoolSubject.PHYSICAL_EDUCATION );
        Assertions.assertEquals(4, teachers.size());
    }

    @Test
    void testRetrieveTeachersTeachingBiologyAndInMoreThan2Schools() {
        School school1 = new School("School 1", "City 1", "Address 1");
        School school2 = new School("School 2", "City 2", "Address 2");
        School school3 = new School("School 3", "City 3", "Address 3");

        Teacher teacher1 = new Teacher("John", "Doe", SchoolSubject.PHYSICAL_EDUCATION);

        teacher1.addSchool(school1);
        teacher1.addSchool(school2);
        teacher1.addSchool(school3);

        school1.addTeacher(teacher1);
        school2.addTeacher(teacher1);
        school3.addTeacher(teacher1);

        schoolService.saveSchool(school1);
        schoolService.saveSchool(school2);
        schoolService.saveSchool(school3);

        List<Teacher> teachers = teacherService.getTeachersTeachingSubjectAndTeachingInNumberOfSchools(SchoolSubject.PHYSICAL_EDUCATION, 2);
        Assertions.assertEquals(1, teachers.size());
    }
}