package com.demo.Attendance.util;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.*;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public void updateStudentDetails(Student student, StudentRequestDto studentRequestDto) {

        User studentUser = student.getUser();

        if (isValid(studentRequestDto.getEmail())) {
            student.setEmail(studentRequestDto.getEmail());
        }
        if (isValid(studentRequestDto.getPhoneNumber())) {
            student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        }
        if (isValid(studentRequestDto.getPassword())) {
            updateInstructorPassword(student, studentRequestDto.getPassword());
        }

        String firstNameUpdate = studentRequestDto.getFirstName();
        String lastNameUpdate = studentRequestDto.getLastName();

        if (isValid(firstNameUpdate)) {
            student.setFirstName(firstNameUpdate);
        }
        if (isValid(lastNameUpdate)) {
            student.setLastName(lastNameUpdate);
        }

        // Update userName based on new or existing first, last names
        String UserName = (firstNameUpdate != null ? firstNameUpdate : student.getFirstName())
                + (lastNameUpdate != null ? lastNameUpdate : student.getLastName());

        studentUser.setUserName(UserName.toLowerCase());

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
