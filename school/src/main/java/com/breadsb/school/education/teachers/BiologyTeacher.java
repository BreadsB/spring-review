package com.breadsb.springreview.education.teachers;

import com.breadsb.springreview.education.schools.School;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BiologyTeacher implements Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate birthDate;
    @NotNull
    @Column(name = "schoolList")
    @ManyToMany
    private List<School> schools;

    public BiologyTeacher(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String getFistName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public List<School> getSchools() {
        return null;
    }
}
