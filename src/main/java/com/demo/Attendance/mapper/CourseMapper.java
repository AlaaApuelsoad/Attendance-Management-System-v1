package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dtoCourse.CourseResponseDto;
import com.demo.Attendance.model.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static Course mapToCourse(CourseRequestDto courseRequestDto){

        Course course = new Course();

        course.setCourseName(courseRequestDto.getCourseName());
        course.setDescription(courseRequestDto.getDescription());

        return course;
    }

    //CourseUpdateResponse Dto
    public static CourseResponseDto mapToCourseUpdateResponseDto(Course course){

        CourseResponseDto courseResponseDto = new CourseResponseDto();

        courseResponseDto.setId(course.getId());
        courseResponseDto.setCourseName(course.getCourseName());
        courseResponseDto.setDescription(course.getDescription());
        courseResponseDto.setInstructorName(course.getInstructors().stream().map(instructor ->
                instructor.getUser().getUserName()).toList().toString());

        return courseResponseDto;
    }


    //Mapping List<CourseUtil> to List<CourseResponseDto>
    public static List<CourseResponseDto> toCourseResponseDtoList(List<Course> courses) {
        return courses.stream()
                .map(CourseMapper::mapToCourseUpdateResponseDto)
                .collect(Collectors.toList());
    }

}
