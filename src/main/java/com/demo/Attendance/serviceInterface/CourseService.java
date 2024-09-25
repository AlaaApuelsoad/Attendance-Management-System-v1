package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dtoCourse.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseDto);

    CourseResponseDto updateCourse(long id,CourseRequestDto courseRequestDto);

    void deleteCourseById(long id);

    CourseResponseDto getCourseById(long id);

    List<CourseResponseDto> getAllCourses();

}
