package com.breadsb.school.education;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(unique = true)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String address;

//    @OneToMany(
//            mappedBy = "school",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Student> students;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "school_teacher",
    joinColumns = @JoinColumn(name = "school_id"),
    inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    public School(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.teachers = new ArrayList<>();
    }

//    public void addStudent(Student student) {
//        students.add(student);
//    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id && Objects.equals(name, school.name) &&
                Objects.equals(city, school.city) &&
                Objects.equals(address, school.address) &&
                Objects.equals(teachers, school.teachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, address, teachers);
    }
}