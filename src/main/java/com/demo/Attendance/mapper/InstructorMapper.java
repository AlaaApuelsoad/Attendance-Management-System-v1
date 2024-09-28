package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstructorMapper {


    ObjectMapper objectMapper = new ObjectMapper();

    public Instructor mapToInstructor(InstructorRequestDto instructorRequestDto) {

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.convertValue(instructorRequestDto, Instructor.class);
    }

    public InstructorResponseDto mapToDto(Instructor instructor) {

        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

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
