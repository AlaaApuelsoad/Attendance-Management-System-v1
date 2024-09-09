package com.demo.Attendance.serviceInterface;

public interface UniqueChecker {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
