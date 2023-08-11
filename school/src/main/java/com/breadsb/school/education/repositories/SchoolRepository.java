package com.breadsb.school.education.repositories;

import com.breadsb.school.education.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
