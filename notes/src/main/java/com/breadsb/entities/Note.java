package com.breadsb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String body;
}
