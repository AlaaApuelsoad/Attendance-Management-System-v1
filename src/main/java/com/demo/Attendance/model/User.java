package com.demo.Attendance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false,length = 100)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonBackReference(value = "adminUserReference")
    private Admin admin;

    @OneToOne(mappedBy = "user")
    @JsonBackReference( value = "studentUserReference")
    private Student student;

    @OneToOne(mappedBy = "user")
    @JsonBackReference(value = "instructorUserReference")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    @JsonBackReference(value = "roleUserReference")
    private Role role ;

}
