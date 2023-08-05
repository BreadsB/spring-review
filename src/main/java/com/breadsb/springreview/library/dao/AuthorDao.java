package com.breadsb.springreview.library.dao;

import com.breadsb.springreview.library.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AuthorDao extends CrudRepository<Author, Integer> {
}
