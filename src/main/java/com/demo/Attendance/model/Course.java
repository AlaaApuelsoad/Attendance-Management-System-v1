package com.demo.Attendance.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

//    @JsonBackReference(value = "studentCourseReference")
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Student> student = new ArrayList<>();

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private List<Instructor> instructors = new ArrayList<>();

}
