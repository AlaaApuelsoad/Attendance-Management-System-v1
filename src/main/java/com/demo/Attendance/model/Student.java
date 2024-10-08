package com.demo.Attendance.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference(value = "studentUserReference")
    private User user;

    @Column(nullable = false,length = 50)
    private String firstName;

    @Column(nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    @Column(nullable = false,length = 11,unique = true)
    private String phoneNumber;

//    @JsonManagedReference(value = "studentCourseReference")
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "enrollment",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
//    @JsonIgnore
    List<Course> courses = new ArrayList<>();


}
