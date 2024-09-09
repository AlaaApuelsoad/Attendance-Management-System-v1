package com.demo.Attendance.controller;

import com.demo.Attendance.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dtoCourse.CourseResponseDto;
import com.demo.Attendance.dtoCourse.CourseUpdateRequestDto;
import com.demo.Attendance.serviceInterface.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto){
        return ResponseEntity.ok(courseService.createCourse(courseRequestDto));
    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable long id, @Valid @RequestBody CourseUpdateRequestDto courseUpdateRequestDto){
        return ResponseEntity.ok(courseService.updateCourse(id,courseUpdateRequestDto));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable long id){
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Course with id- "+id+" Deleted successfully!");
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/courses")
    public List<CourseResponseDto> getAllCourses(){
        return courseService.getAllCourses();
    }

}
