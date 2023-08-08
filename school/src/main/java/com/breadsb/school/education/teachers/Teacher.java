package com.breadsb.springreview.education.teachers;

import com.breadsb.springreview.education.schools.School;

import java.util.List;

public interface Teacher {
    String getFistName();
    String getLastName();
    List<School> getSchools();
}
