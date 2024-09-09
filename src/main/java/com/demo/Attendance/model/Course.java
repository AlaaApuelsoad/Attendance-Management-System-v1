package com.demo.Attendance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message = "CourseUtil name cannot be null")
    @Column(nullable = false,name = "course_name")
    private String courseName;

    @Column(nullable = false,name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<Student> student = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Instructor> instructors = new ArrayList<>();

}
