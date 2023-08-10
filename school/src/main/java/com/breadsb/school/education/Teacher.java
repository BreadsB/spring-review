package com.breadsb.school.education;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(unique = true)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "school_subject")
    @Enumerated(EnumType.STRING)
    private SchoolSubject schoolSubject;

    @ManyToMany(mappedBy = "teachers")
    private List<School> schools;

    public Teacher(String firstName, String lastName, SchoolSubject schoolSubject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolSubject = schoolSubject;
        this.schools = new ArrayList<>();
    }

    public void addSchool(School school) {
        schools.add(school);
    }

    public void removeSchool(School school) {
        schools.remove(school);
    }
}
