package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dto.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dto.dtoCourse.CourseResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseDto);

    CourseResponseDto updateCourse(long id,CourseRequestDto courseRequestDto);

    void deleteCourseById(long id);

    CourseResponseDto getCourseById(long id);

    Page<CourseResponseDto> getAllCourses(Pageable pageable);

    Page<CourseResponseDto> searchByCourseName(String courseName, Pageable pageable);

}
