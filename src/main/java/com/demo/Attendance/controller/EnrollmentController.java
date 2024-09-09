package com.demo.Attendance.controller;

import com.demo.Attendance.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.dtoEnrollment.EnrollmentResponseDto;
import com.demo.Attendance.serviceImplementation.EnrollmentService;
import jakarta.validation.Valid;
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

        return ResponseEntity.ok(enrollmentService.enrollStudent(enrollmentRequestDto));
    }

    @GetMapping("/enrollments/courses/{id}")
    public ResponseEntity<EnrollmentResponseDto> getStudentEnrollInCourse(@PathVariable long id){

        return ResponseEntity.ok(enrollmentService.getStudentEnrollInCourse(id));
    }
}
