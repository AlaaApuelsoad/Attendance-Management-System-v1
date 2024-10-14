package com.demo.Attendance.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course",indexes = {
        @Index(name = "courseName",columnList = "courseName")
})
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, length = 50)
    private String courseName;

    @Column(nullable = false, length = 150)
    private String description;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> student = new HashSet<>();

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private Set<Instructor> instructors = new HashSet<>();

}
