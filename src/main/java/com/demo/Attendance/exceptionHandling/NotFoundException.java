package com.demo.Attendance.exceptionHandling;

public class NotFoundException extends  RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

}
