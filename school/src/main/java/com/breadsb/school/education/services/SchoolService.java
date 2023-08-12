package com.breadsb.school.education.services;

import com.breadsb.school.education.School;
import com.breadsb.school.education.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository repository;

    @Autowired
    SchoolService(SchoolRepository schoolRepository) {
        this.repository = schoolRepository;
    }

    public School getSchool(long id) {
        return repository.findById(id).orElse(null);
    }

    public void removeSchool(long id) {
        repository.deleteById(id);
    }

    public void saveSchool(School school) {
        repository.save(school);
    }

    public void updateSchool(long id, School school) {
        School schoolFromRepo = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        schoolFromRepo.setName(school.getName());
        schoolFromRepo.setAddress(school.getAddress());
        schoolFromRepo.setCity(school.getCity());
        if (school.getTeachers()!=null) {
            schoolFromRepo.setTeachers(school.getTeachers());
        }
    }

    public List<School> getSchools() {
        return repository.findAll();
    }
}