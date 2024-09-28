package com.demo.Attendance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, length = 50)
    private String courseName;

    @Column(nullable = false, length = 150)
    private String description;

//    @JsonBackReference(value = "studentCourseReference")
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Student> student = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Instructor> instructors = new ArrayList<>();

}
