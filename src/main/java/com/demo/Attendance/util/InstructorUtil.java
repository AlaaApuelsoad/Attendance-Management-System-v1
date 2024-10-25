package com.demo.Attendance.util;

import com.demo.Attendance.dto.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public  class InstructorUtil {

    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public InstructorUtil(InstructorRepository instructorRepository, RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.instructorRepository = instructorRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Instructor findInstructorById(long id){
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstantMessages.INSTRUCTOR_WITH_ID +" "+ id+" "+ ConstantMessages.NOT_FOUND));
    }

    public User setUserForInstructor(InstructorRequestDto instructorRequestDto){

        Role instructorRole = roleRepository.findByRoleName("INSTRUCTOR");

        User instructorUser = new User();
        instructorUser.setRole(instructorRole);
        instructorUser.setUserName(instructorRequestDto.getFirstName().toLowerCase()+
                instructorRequestDto.getLastName().toLowerCase());
        instructorUser.setPassword(bCryptPasswordEncoder.encode(instructorRequestDto.getPassword()));

        return instructorUser;
    }

    public void updateInstructorDetails(Instructor instructor , InstructorRequestDto instructorRequestDto){

        User instructorUser = instructor.getUser();
        if (isValid(instructorRequestDto.getEmail())){
            instructor.setEmail(instructorRequestDto.getEmail());
        }
        if (isValid(instructorRequestDto.getPhoneNumber())){
            instructor.setPhoneNumber(instructorRequestDto.getPhoneNumber());
        }
        if (isValid(instructorRequestDto.getPassword())){
            updateInstructorPassword(instructor,instructorRequestDto.getPassword());
        }

        String firstNameUpdate = instructorRequestDto.getFirstName();
        String lastNameUpdate = instructorRequestDto.getLastName();

        if (isValid(firstNameUpdate)){
            instructor.setFirstName(firstNameUpdate);
        }
        if (isValid(lastNameUpdate)){
            instructor.setLastName(lastNameUpdate);
        }

        String userName = (firstNameUpdate != null ? firstNameUpdate : instructor.getFirstName())
                + (lastNameUpdate != null ? lastNameUpdate : instructor.getLastName());

        instructorUser.setUserName(userName.toLowerCase());
    }

    private boolean isValid(String value){
        return value !=null && !value.trim().isEmpty();
    }

    private void updateInstructorPassword(Instructor instructor, String newPassword) {

        User instructorUser = instructor.getUser();
        instructorUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        instructor.setUser(instructorUser);
    }

    public void updateUserNameWithId(long id ,User user){
        String newUserName = user.getUserName()+id;
        user.setUserName(newUserName);

    }
}
