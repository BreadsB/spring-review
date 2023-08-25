package com.breadsb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String body;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}