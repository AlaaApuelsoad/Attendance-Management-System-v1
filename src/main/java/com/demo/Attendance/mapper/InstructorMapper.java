package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstructorMapper {

    @Autowired
    private ObjectMapper objectMapper;
    public Instructor mapToInstructor(InstructorRequestDto instructorRequestDto) {
        System.out.println(objectMapper);
        return objectMapper.convertValue(instructorRequestDto, Instructor.class);
    }

    public InstructorResponseDto mapToDto(Instructor instructor) {

        System.out.println(objectMapper);

        InstructorResponseDto instructorResponseDto = objectMapper.convertValue(instructor, InstructorResponseDto.class);

        if (instructor.getUser() != null) {
            instructorResponseDto.setUserName(instructor.getUser().getUserName());
            instructorResponseDto.setRoleName(instructor.getUser().getRole().getRoleName());
        }
        if (!instructor.getCourses().isEmpty()){
            instructorResponseDto.setCoursesName(instructor.getCourses().stream().map(Course::getCourseName).toList());
        }
        return instructorResponseDto;

    }

    public List<InstructorResponseDto> mapToDtoList(List<Instructor> instructors) {
        return instructors.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
