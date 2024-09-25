package com.demo.Attendance.util;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class InstructorUtil {

    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InstructorUtil(InstructorRepository instructorRepository, RoleRepository roleRepository,
                          CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.instructorRepository = instructorRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Instructor findInstructorById(long id){
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstantMessages.instructorNotFound +" "+ id+" "+ ConstantMessages.notFoundMessage));
    }

    public User setUserForInstructor(InstructorRequestDto instructorRequestDto){

        Role instructorRole = roleRepository.findByRoleName("ROLE_INSTRUCTOR");

        User instructorUser = new User();
        instructorUser.setRole(instructorRole);
        instructorUser.setUserName(instructorRequestDto.getFirstName().toLowerCase()+
                instructorRequestDto.getLastName().toLowerCase()+"2024");
        instructorUser.setPassword(passwordEncoder.encode(instructorRequestDto.getPassword()));

        return instructorUser;
    }

    public List<Course> validateCourse(List<Long> courseIds){

        List<Course> courses = courseRepository.findAllById(courseIds);

        if (courses.size() !=courseIds.size()){

            List<Long> foundCourseIds = courses.stream().map(Course::getId).toList();
            List<Long> notFoundCourseIds = courseIds.stream().
                    filter(courseId -> !foundCourseIds.contains(courseId)).toList();
            throw new NotFoundException(ConstantMessages.courseNotFound+notFoundCourseIds+ConstantMessages.notFoundMessage);
        }

        return courses;
    }

    public void updateInstructorCourse(Instructor instructor,List<Course> courses){
        instructor.getCourses().addAll(courses);
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
        instructorUser.setPassword(passwordEncoder.encode(newPassword));
        instructor.setUser(instructorUser);
    }



    public void updateUserNameWithId(long id ,User user){
        String newUserName = user.getUserName()+id;
        user.setUserName(newUserName);

    }


}
