package com.breadsb.springreview.library;

import com.breadsb.springreview.library.dao.BookDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class BookTest {

    @Autowired
    BookDao bookDao;

    @Test
    void givenBook_whenDaoSave_thenRetrieveBook() {
        Author author = new Author("John", "Smith", LocalDate.of(1990, 3, 20));
        Book book = new Book("The Great Gatsby", author, LocalDate.of(1925, 4, 10));

        bookDao.save(book);
        int id = book.getId();

        Optional<Book> dbBook = bookDao.findById(id);
        Assertions.assertTrue(dbBook.isPresent());
    }
}
