package com.breadsb.school.education.repositories;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllBySchoolSubject(SchoolSubject schoolSubject);

    @Query
    List<Teacher> retrieveTeachersWithSubjectAndSchoolsGreaterThen(@Param("ss")SchoolSubject ss, @Param("sq")int schools);
}