package com.demo.Attendance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Data
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false,length = 50)
    private String courseName;

    @Column(nullable = false,length = 150)
    private String description;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<Student> student = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Instructor> instructors = new ArrayList<>();

}
