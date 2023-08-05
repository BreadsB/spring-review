package com.breadsb.springreview.library.dao;

import com.breadsb.springreview.library.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookDao extends CrudRepository<Book, Integer> {
}
