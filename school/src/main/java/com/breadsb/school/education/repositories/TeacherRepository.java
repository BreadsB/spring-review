package com.breadsb.school.education.repositories;

import com.breadsb.school.education.SchoolSubject;
import com.breadsb.school.education.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findBySchoolSubject(SchoolSubject schoolSubject);

    @Query
    List<Teacher> retrieveTeachersWithSubjectAndSchoolsGreaterThen(SchoolSubject ss, int schools);
}
