package com.demo.Attendance.controller;

import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignRequestDto;
import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignResponseDto;
import com.demo.Attendance.serviceImplementation.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @PostMapping("/assignments")
    public ResponseEntity<InstructorCourseAssignResponseDto> AssignInstructorToCourse(@Validated @RequestBody InstructorCourseAssignRequestDto instructorCourseAssignRequestDto) {

        return new ResponseEntity<>(assignmentService.assignInstructorToCourse(instructorCourseAssignRequestDto), HttpStatus.CREATED);
    }
}
