package com.demo.Attendance.controller;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.dtoInstructor.InstructorUpdateRequestDto;
import com.demo.Attendance.serviceInterface.InstructorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    private InstructorService instructorService;


    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/instructors")
    public ResponseEntity<InstructorResponseDto> createInstructor(@Valid @RequestBody InstructorRequestDto instructorRequestDto){

        return ResponseEntity.ok(instructorService.createInstructor(instructorRequestDto));
    }

    @PatchMapping("/instructors/{id}")
    public ResponseEntity<InstructorResponseDto> updateInstructor(@PathVariable long id,
                                                                @Valid @RequestBody InstructorUpdateRequestDto instructorUpdateRequestDto){

        return ResponseEntity.ok(instructorService.updateInstructor(id,instructorUpdateRequestDto));
    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable long id){

        instructorService.deleteInstructor(id);
        return ResponseEntity.ok("Instructor with id- "+id+" Deleted successfully!");
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructorById(@PathVariable long id){

        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @GetMapping("/instructors")
    public List<InstructorResponseDto> getAllInstructors(){

        return instructorService.getAllInstructor();
    }

}
