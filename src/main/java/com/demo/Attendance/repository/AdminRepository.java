package com.demo.Attendance.repository;

import com.demo.Attendance.model.Admin;
import com.demo.Attendance.serviceInterface.UniqueChecker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>,UniqueChecker {

    @Override
    boolean existsByEmail(String email);

    @Override
    boolean existsByPhoneNumber(String phoneNumber);

    @Override
    Page<Admin> findAll(Pageable pageable);

    @Query("SELECT a FROM Admin a JOIN FETCH User u ON a.user.id = u.id " +
            "WHERE a.firstName LIKE %?1% OR a.lastName LIKE %?1% ORDER BY a.firstName")
    Page<Admin> searchAdminByName(String name,Pageable pageable);

}
