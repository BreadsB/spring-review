package com.breadsb.springreview.library.dao;

import com.breadsb.springreview.library.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
}
