package com.breadsb.springreview.education.students;

import com.breadsb.springreview.education.schools.School;
import jakarta.persistence.*;
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
    private String name;
    private String lastName;

    @ManyToOne
    private School school;

    public Student(String name, String lastName, School school) {
        this.name = name;
        this.lastName = lastName;
        this.school = school;
    }
}
