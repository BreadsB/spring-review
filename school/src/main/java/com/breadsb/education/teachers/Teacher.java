package com.breadsb.education.teachers;

import com.breadsb.education.schools.School;

import java.util.List;

public interface Teacher {
    String getFistName();
    String getLastName();
    List<School> getSchools();
}
