package com.demo.Attendance.repository;

import com.demo.Attendance.model.Student;
import com.demo.Attendance.serviceInterface.UniqueChecker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, UniqueChecker {

    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByPhoneNumber(String phoneNumber);
}
