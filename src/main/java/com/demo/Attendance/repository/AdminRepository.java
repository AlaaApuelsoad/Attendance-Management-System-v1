package com.demo.Attendance.repository;

import com.demo.Attendance.model.Admin;
import com.demo.Attendance.serviceInterface.UniqueChecker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>,UniqueChecker {

    @Override
    Page<Admin> findAll(Pageable pageable);

    Admin findAdminByEmail(String email);

    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByPhoneNumber(String phoneNumber);

//    @Query("SELECT a FROM Admin a WHERE a.firstName LIKE %:firstName%")
    @Query("SELECT a FROM Admin a JOIN FETCH User u on a.user.id = u.id where a.firstName LIKE %:firstName% ")
    Page<Admin> searchAdminByFirstNameAndLastName(@RequestParam("firstName") String firstName, Pageable pageable);

}
