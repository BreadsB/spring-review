package com.breadsb.education.schools.factory;

import com.breadsb.education.schools.JuniorSchool;
import com.breadsb.education.schools.School;
import org.springframework.stereotype.Component;

@Component
public class JuniorSchoolFactory implements SchoolFactory {
    @Override
    public School createSchool() {
        return new JuniorSchool();
    }
}