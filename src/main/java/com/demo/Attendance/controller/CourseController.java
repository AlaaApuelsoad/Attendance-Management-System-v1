package com.demo.Attendance.controller;

import com.demo.Attendance.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dtoCourse.CourseResponseDto;
import com.demo.Attendance.serviceInterface.CourseService;
import com.demo.Attendance.serviceInterface.OnCreate;
import com.demo.Attendance.serviceInterface.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponseDto> createCourse
            (@Validated(OnCreate.class) @RequestBody CourseRequestDto courseRequestDto){
        return new ResponseEntity<>(courseService.createCourse(courseRequestDto),HttpStatus.CREATED);
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse
            (@PathVariable long id, @Validated(OnUpdate.class) @RequestBody CourseRequestDto courseRequestDto){
        return new ResponseEntity<>(courseService.updateCourse(id,courseRequestDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable long id){
        courseService.deleteCourseById(id);
        return new ResponseEntity<>("Course with id- "+id+" Deleted successfully!",HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable long id){
        return new ResponseEntity<>(courseService.getCourseById(id),HttpStatus.FOUND);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

}
