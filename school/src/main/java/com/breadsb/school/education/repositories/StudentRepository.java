package com.breadsb.school.education.repositories;

import com.breadsb.school.education.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {
}
