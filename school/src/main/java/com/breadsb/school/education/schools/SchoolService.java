package com.breadsb.springreview.education.schools;

import com.breadsb.springreview.education.schools.factory.HighSchoolFactory;
import com.breadsb.springreview.education.schools.factory.JuniorSchoolFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SchoolService {

    private final HighSchoolFactory hsf;
    private final JuniorSchoolFactory jsf;
}
