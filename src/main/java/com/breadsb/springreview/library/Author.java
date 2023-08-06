package com.breadsb.springreview.library;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private int id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private LocalDate birthday;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Book> bookList;

    public Author(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void removeBook(Book book) {
        bookList.remove(book);
    }
}
