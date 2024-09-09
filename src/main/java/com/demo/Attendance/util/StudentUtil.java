package com.demo.Attendance.util;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentUpdateRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.*;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentUtil {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Autowired
    public StudentUtil(StudentRepository studentRepository, PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Student findStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstantMessages.studentNotFound+id+ConstantMessages.notFoundMessage));
    }

    public User setUserForStudent(StudentRequestDto studentRequestDto) {

        Role studentRole = roleRepository.findByRoleName("ROLE_STUDENT");

        User studentUser = new User();
        studentUser.setRole(studentRole);
        studentUser.setUserName(studentRequestDto.getFirstName().toLowerCase() +
                studentRequestDto.getLastName().toLowerCase() + "2024");
        studentUser.setPassword(passwordEncoder.encode(studentRequestDto.getPassword()));
        return studentUser;
    }

    public void updateStudentDetails(Student student, StudentUpdateRequestDto studentUpdateRequestDto) {

        if (isValid(studentUpdateRequestDto.getEmail())) {
            student.setEmail(studentUpdateRequestDto.getEmail());
        }
        if (isValid(studentUpdateRequestDto.getPhoneNumber())) {
            student.setPhoneNumber(studentUpdateRequestDto.getPhoneNumber());
        }
        if (isValid(studentUpdateRequestDto.getPassword())) {
            updateInstructorPassword(student, studentUpdateRequestDto.getPassword());
        }
    }

    private boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private void updateInstructorPassword(Student student, String newPassword) {

        User studentUser = student.getUser();
        studentUser.setPassword(passwordEncoder.encode(newPassword));
        student.setUser(studentUser);
    }


    public void updateUserNameWithId(long id, User user) {
        String newUserName = user.getUserName() + id;
        user.setUserName(newUserName);
    }


}
