package com.breadsb.springreview.library;

import com.breadsb.springreview.library.dao.AuthorDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class AuthorTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void givenAuthor_whenDaoSave_thenCheckIfPresent() {
        Author author = new Author();
        author.setFirstName("Mike");
        author.setLastName("Tyson");
        author.setBirthday(LocalDate.of(1980,7,14));
        authorDao.save(author);
        int id = author.getId();

        Optional<Author> dbAuthor = authorDao.findById(id);
        Assertions.assertTrue(dbAuthor.isPresent());

//        authorDao.deleteById(id);
    }
}