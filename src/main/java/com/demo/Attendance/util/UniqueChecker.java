package com.demo.Attendance.util;

import com.demo.Attendance.exceptionHandling.AlreadyExistsException;
import com.demo.Attendance.repository.AdminRepository;
import com.demo.Attendance.repository.InstructorRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueChecker {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UniqueChecker(StudentRepository studentRepository, InstructorRepository instructorRepository,
                         AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.adminRepository = adminRepository;
    }


    public void emailUniqueChecker(String email){

        boolean existsStudent = studentRepository.existsByEmail(email);
        boolean existsInstructor = instructorRepository.existsByEmail(email);
        boolean existsAdmin = adminRepository.existsByEmail(email);
        System.out.println(existsStudent);
        System.out.println(existsInstructor);
        System.out.println(existsAdmin);

        if (existsStudent || existsInstructor || existsAdmin){
            throw new AlreadyExistsException("Email already exists in the system");
        }
    }

    public void phoneNumberUniqueChecker(String phoneNumber){

        boolean existsStudent = studentRepository.existsByPhoneNumber(phoneNumber);
        boolean existsInstructor = instructorRepository.existsByPhoneNumber(phoneNumber);
        boolean existsAdmin = adminRepository.existsByPhoneNumber(phoneNumber);
        System.out.println(existsStudent);
        System.out.println(existsInstructor);
        System.out.println(existsAdmin);

        if (existsStudent || existsInstructor || existsAdmin){
            throw new AlreadyExistsException("Phone Number already exists in the system");
        }
    }


}
