package com.breadsb.education.students;

import com.breadsb.education.schools.School;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String firstName, String lastName, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.school = school;
    }
}
