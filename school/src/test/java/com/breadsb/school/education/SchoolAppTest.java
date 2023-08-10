package com.breadsb.school.education;

import com.breadsb.school.education.services.SchoolService;
import com.breadsb.school.education.services.StudentService;
import com.breadsb.school.education.services.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class SchoolAppTest {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SchoolService schoolService;

    @Test
    void test() {
        Student student = new Student("Mike", "Morrison", LocalDate.of(1990,1,7));
        School school = new School("Super hyper Junior School", "Washington", "17th Street a");
        Teacher teacher = new Teacher("Thomas", "Shelby", SchoolSubject.BIOLOGY);
        school.addTeacher(teacher);
        student.setSchool(school);

        schoolService.addSchool(school);
        long id = school.getId();
        School retrievedSchool = schoolService.getSchool(id);

        Assertions.assertNotNull(retrievedSchool);
    }
}
