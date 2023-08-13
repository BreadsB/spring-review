package com.breadsb.school.education.services;

import com.breadsb.school.education.School;
import com.breadsb.school.education.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;

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