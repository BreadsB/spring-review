package com.breadsb.school.education;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SchoolAppTest {

    @Test
    void test() {
        Student student = new Student("Mike", "Morrison", LocalDate.of(1990,1,7));
        School school = new School("Super hyper Junior School", "Washington", "17th Street a");
    }
}
