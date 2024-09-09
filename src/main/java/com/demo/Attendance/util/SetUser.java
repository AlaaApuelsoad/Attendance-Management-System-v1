package com.demo.Attendance.util;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetUser {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SetUser(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User setUserForStudent(StudentRequestDto studentRequestDto) {

        Role studentRole = roleRepository.findByRoleName("student");

        User studentUser = new User();

        studentUser.setRole(studentRole);

        studentUser.setUserName(studentRequestDto.getFirstName().toLowerCase().
                concat(studentRequestDto.getLastName().toLowerCase()).concat("2024"));

        studentUser.setPassword(passwordEncoder.encode(studentRequestDto.getPassword()));

        return studentUser;
    }

    public User setUserForInstructor(InstructorRequestDto instructorRequestDto) {

        Role instructorRole = roleRepository.findByRoleName("instructor");

        User instructorUser = new User();

        instructorUser.setRole(instructorRole);

        instructorUser.setUserName(instructorRequestDto.getFirstName().toLowerCase()
                .concat(instructorRequestDto.getLastName().toLowerCase()).concat("2024"));

        instructorUser.setPassword(passwordEncoder.encode(instructorRequestDto.getPassword()));

        return instructorUser;
    }

    public User setUserForAdmin(AdminRequestDto adminRequestDto) {

        Role adminRole = roleRepository.findByRoleName("admin");

        User adminUser = new User();
        adminUser.setRole(adminRole);
        adminUser.setUserName(adminRequestDto.getFirstName().toLowerCase().
                concat(adminRequestDto.getLastName().toLowerCase()));

        adminUser.setPassword(passwordEncoder.encode(adminRequestDto.getPassword()));

        return adminUser;
    }

    public void updateUserNameWithId(User user, long id) {
        String updateUserName = user.getUserName() + id;
        user.setUserName(updateUserName);
    }

}
