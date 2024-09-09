package com.demo.Attendance.controller;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.dtoStudent.StudentUpdateRequestDto;
import com.demo.Attendance.serviceInterface.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {

    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto){
        return ResponseEntity.ok(studentService.createStudent(studentRequestDto));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable long id, @Valid @RequestBody StudentUpdateRequestDto studentUpdateRequestDto){

        return ResponseEntity.ok(studentService.updateStudent(id,studentUpdateRequestDto));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable long id){

        studentService.deleteStudentById(id);
        return ResponseEntity.ok("student with id- "+id+" Deleted successfully!");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable long id){

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(){

        return studentService.getAllStudents();
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

}
