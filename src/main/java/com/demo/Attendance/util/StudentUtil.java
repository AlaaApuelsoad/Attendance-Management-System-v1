package com.demo.Attendance.util;

import com.demo.Attendance.dto.dtoStudent.StudentRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.*;
import com.demo.Attendance.repository.RoleRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StudentUtil {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;


    @Autowired
    public StudentUtil(StudentRepository studentRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public Student findStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstantMessages.STUDENT_WITH_ID +id+ConstantMessages.NOT_FOUND));
    }

    public User setUserForStudent(StudentRequestDto studentRequestDto) {

        String yearString = new SimpleDateFormat("yyyy").format(new Date());
        Role studentRole = roleRepository.findByRoleName("STUDENT");

        User studentUser = new User();
        studentUser.setRole(studentRole);
        studentUser.setUserName(studentRequestDto.getFirstName().toLowerCase() +
                studentRequestDto.getLastName().toLowerCase() + yearString);
        studentUser.setPassword(bCryptPasswordEncoder.encode(studentRequestDto.getPassword()));
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
            updateStudentPassword(student, studentRequestDto.getPassword());
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

    private void updateStudentPassword(Student student, String newPassword) {

        User studentUser = student.getUser();
        studentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        student.setUser(studentUser);
    }


    public void updateUserNameWithId(long id, User user) {
        String newUserName = user.getUserName() + id;
        user.setUserName(newUserName);
    }

}
