package com.demo.Attendance.repository;

import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.serviceInterface.UniqueChecker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long>, UniqueChecker {


    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT i FROM Instructor i WHERE i.firstName LIKE %?1% OR i.lastName LIKE %?1% ORDER BY i.firstName")
    Page<Instructor> searchByInstructorName(String name,Pageable pageable);

    @Override
    Page<Instructor> findAll(Pageable pageable);


}
