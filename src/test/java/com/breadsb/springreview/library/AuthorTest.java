package com.breadsb.springreview.library;

import com.breadsb.springreview.library.dao.AuthorDao;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
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

        authorDao.deleteById(id);
    }

    @Test
    @Transactional // Automatic rollback after test is finished
    void findAuthorsHavingMoreThan2Books() {
        Author author1 = new Author("Jane", "Austen", LocalDate.of(1775, 12, 16));
        Author author2 = new Author("Charles", "Dickens", LocalDate.of(1812, 2, 7));
        Author author3 = new Author("Mark", "Twain", LocalDate.of(1835, 11, 30));
        Author author4 = new Author("Agatha", "Christie", LocalDate.of(1890, 9, 15));
        Author author5 = new Author("J.K.", "Rowling", LocalDate.of(1965, 7, 31));

        Book book1 = new Book("Pride and Prejudice", author1, LocalDate.of(1813, 1, 28));
        Book book2 = new Book("Sense and Sensibility", author1, LocalDate.of(1811, 1, 1));
        Book book3 = new Book("A Tale of Two Cities", author2, LocalDate.of(1859, 4, 30));
        Book book4 = new Book("Adventures of Huckleberry Finn", author3, LocalDate.of(1884, 12, 10));
        Book book5 = new Book("The Adventures of Tom Sawyer", author3, LocalDate.of(1876, 2, 17));
        Book book6 = new Book("Murder on the Orient Express", author4, LocalDate.of(1934, 1, 1));
        Book book7 = new Book("Harry Potter and the Philosopher's Stone", author5, LocalDate.of(1997, 6, 26));
        Book book8 = new Book("Harry Potter and the Chamber of Secrets", author5, LocalDate.of(1998, 7, 2));
        Book book9 = new Book("Harry Potter and the Prisoner of Azkaban", author5, LocalDate.of(1999, 7, 8));
        Book book10 = new Book("Harry Potter and the Goblet of Fire", author5, LocalDate.of(2000, 7, 8));
        Book book11 = new Book("Harry Potter and the Order of the Phoenix", author5, LocalDate.of(2003, 6, 21));

        author1.addBook(book1);
        author1.addBook(book2);
        author2.addBook(book3);
        author3.addBook(book4);
        author3.addBook(book5);
        author4.addBook(book6);
        author5.addBook(book7);
        author5.addBook(book8);
        author5.addBook(book9);
        author5.addBook(book10);
        author5.addBook(book11);

        authorDao.save(author1);
        authorDao.save(author2);
        authorDao.save(author3);
        authorDao.save(author4);
        authorDao.save(author5);

        List<Author> authorsWithMoreThan3Books = authorDao.findByBookListGreaterThanEqual(3);
        Assertions.assertFalse(authorsWithMoreThan3Books.isEmpty());
    }
}