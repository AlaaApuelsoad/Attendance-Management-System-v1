package com.demo.Attendance.repository;

import com.demo.Attendance.model.Student;
import com.demo.Attendance.serviceInterface.UniqueChecker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, UniqueChecker {

    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByPhoneNumber(String phoneNumber);

    @Override
    Page<Student> findAll(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %?1% OR s.lastName LIKE %?1% ORDER BY s.firstName")
    Page<Student> searchStudentByName(String name,Pageable pageable);

}
