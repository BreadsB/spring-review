package com.breadsb.springreview.education.schools.factory;

import com.breadsb.springreview.education.schools.HighSchool;
import com.breadsb.springreview.education.schools.School;
import org.springframework.stereotype.Component;

@Component
public class HighSchoolFactory implements SchoolFactory {
    @Override
    public School createSchool() {
        return new HighSchool();
    }
}