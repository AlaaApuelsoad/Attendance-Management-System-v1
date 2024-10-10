package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dto.dtoCourse.CourseResponseDto;
import com.demo.Attendance.model.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {


    private final ObjectMapper objectMapper;

    public CourseMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Course matToCourse(CourseRequestDto courseRequestDto) {
        return objectMapper.convertValue(courseRequestDto, Course.class);
    }

    public CourseResponseDto mapToDto(Course course) {

        CourseResponseDto courseResponseDto = objectMapper.convertValue(course, CourseResponseDto.class);
        courseResponseDto.setInstructorName(courseResponseDto.getInstructorName());
        return courseResponseDto;
    }


    public List<CourseResponseDto> mapToDtoList(List<Course> courses) {
        return courses.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
