package com.breadsb.school.education.repositories;

import com.breadsb.school.education.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
