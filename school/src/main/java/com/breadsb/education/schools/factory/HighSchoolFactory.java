package com.breadsb.education.schools.factory;

import com.breadsb.education.schools.HighSchool;
import com.breadsb.education.schools.School;
import org.springframework.stereotype.Component;

@Component
public class HighSchoolFactory implements SchoolFactory {
    @Override
    public School createSchool() {
        return new HighSchool();
    }
}