package com.demo.Attendance.controller;

import com.demo.Attendance.dto.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dto.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.serviceInterface.InstructorService;
import com.demo.Attendance.serviceInterface.OnCreate;
import com.demo.Attendance.serviceInterface.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class InstructorController {

    private final InstructorService instructorService;


    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/instructors")
    public ResponseEntity<InstructorResponseDto> createInstructor
            (@Validated(OnCreate.class) @RequestBody InstructorRequestDto instructorRequestDto){

        return new ResponseEntity<>(instructorService.createInstructor(instructorRequestDto),HttpStatus.CREATED);
    }

    @PatchMapping("/instructors/{id}")
    public ResponseEntity<InstructorResponseDto> updateInstructor
            (@PathVariable long id, @Validated(OnUpdate.class) @RequestBody InstructorRequestDto instructorRequestDto){

        return new ResponseEntity<>(instructorService.updateInstructor(id,instructorRequestDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable long id){

        instructorService.deleteInstructor(id);
        return new ResponseEntity<>("Instructor with id- "+id+" Deleted successfully!",HttpStatus.ACCEPTED);
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructorById(@PathVariable long id){

        return new ResponseEntity<>(instructorService.getInstructorById(id),HttpStatus.FOUND);
    }

    @GetMapping("/instructors")
    public ResponseEntity<Page<InstructorResponseDto>> getAllInstructors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size){

        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(instructorService.getAllInstructor(pageable), HttpStatus.ACCEPTED);
    }

    @GetMapping("/instructors/search")
    public ResponseEntity<Page<InstructorResponseDto>> searchInstructorByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size){

        Pageable pageable = PageRequest.of(page, size);

        return new ResponseEntity<>(instructorService.searchByInstructorName(name,pageable),HttpStatus.ACCEPTED);
    }

}
