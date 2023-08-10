package com.breadsb.school.education;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    private long id;
    private String firstName;
    private String lastName;
    private SchoolSubject schoolSubject;
    private List<School> schools;

    public Teacher(String firstName, String lastName, SchoolSubject schoolSubject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolSubject = schoolSubject;
    }

    public void addSchool(School school) {
        schools.add(school);
    }
}
