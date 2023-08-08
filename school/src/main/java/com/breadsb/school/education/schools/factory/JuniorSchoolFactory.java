package com.breadsb.springreview.education.schools.factory;

import com.breadsb.springreview.education.schools.JuniorSchool;
import com.breadsb.springreview.education.schools.School;
import org.springframework.stereotype.Component;

@Component
public class JuniorSchoolFactory implements SchoolFactory {
    @Override
    public School createSchool() {
        return new JuniorSchool();
    }
}