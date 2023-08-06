package com.breadsb.springreview.library.dao;

import com.breadsb.springreview.library.Author;
import com.breadsb.springreview.library.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
}
