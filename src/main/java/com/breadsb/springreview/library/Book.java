package com.breadsb.springreview.library;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    @NotNull
    private int id;

    @NotNull
    private String title;

    @NotNull
    @ManyToOne
    private Author author;

    @NotNull
    private LocalDate releaseDate;
}
