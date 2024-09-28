package com.demo.Attendance.controller;

import com.demo.Attendance.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.dtoEnrollment.EnrollmentResponseDto;
import com.demo.Attendance.serviceImplementation.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnrollmentController {

    public EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enrollments")
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(@Valid @RequestBody EnrollmentRequestDto enrollmentRequestDto){

        return new ResponseEntity<>(enrollmentService.enrollStudent(enrollmentRequestDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/enrollments/courses/{id}")
    public ResponseEntity<EnrollmentResponseDto> getStudentEnrollInCourse(@PathVariable long id){

        return new ResponseEntity<>(enrollmentService.getStudentEnrollInCourse(id),HttpStatus.FOUND);
    }
}
