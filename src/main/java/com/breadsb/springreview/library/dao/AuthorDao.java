package com.breadsb.springreview.library.dao;

import com.breadsb.springreview.library.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorDao extends CrudRepository<Author, Integer> {

    @Query("select a from Author a where size(a.bookList) >= 3")
    List<Author> findByBookListGreaterThanEqual(int quantity);
}