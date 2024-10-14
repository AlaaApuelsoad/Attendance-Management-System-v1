package com.demo.Attendance.controller;

import com.demo.Attendance.dto.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dto.dtoStudent.StudentResponseDto;
import com.demo.Attendance.serviceInterface.StudentService;
import com.demo.Attendance.serviceInterface.OnCreate;
import com.demo.Attendance.serviceInterface.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponseDto> createStudent
            (@Validated(OnCreate.class) @RequestBody StudentRequestDto studentRequestDto){
        return new ResponseEntity<>(studentService.createStudent(studentRequestDto), HttpStatus.CREATED);
    }


    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent
            (@PathVariable long id, @Validated(OnUpdate.class) @RequestBody StudentRequestDto studentRequestDto){

        return new ResponseEntity<>(studentService.updateStudent(id,studentRequestDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable long id){

        studentService.deleteStudentById(id);
        return new ResponseEntity<>("student with id- "+id+" Deleted successfully!",HttpStatus.ACCEPTED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable long id){

        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.FOUND);
    }

    @GetMapping("/students")
    public ResponseEntity<Page<StudentResponseDto>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size){

        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(studentService.getAllStudents(pageable),HttpStatus.ACCEPTED);
    }

    @GetMapping("/students/search")
    public ResponseEntity<Page<StudentResponseDto>> searchStudents(
            @RequestParam() String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size){

        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(studentService.searchStudentByName(name,pageable),HttpStatus.ACCEPTED);
    }




//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }

    //    @PostMapping("/students")
//    public ResponseEntity<StudentResponseDto> createStudent
//            (@Validated(OnCreate.class) @RequestBody StudentRequestDto studentRequestDto){
//
//        StudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdStudent.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).body(createdStudent);
//    }

}
