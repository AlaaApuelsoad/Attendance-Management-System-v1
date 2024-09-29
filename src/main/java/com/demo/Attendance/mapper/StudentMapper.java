package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    @Autowired
    private ObjectMapper objectMapper;
    public Student mapToStudent(StudentRequestDto studentRequestDto){
        System.out.println(objectMapper);
        return objectMapper.convertValue(studentRequestDto, Student.class);
    }

    public StudentResponseDto mapToDto(Student student){

        StudentResponseDto studentResponseDto = objectMapper.convertValue(student, StudentResponseDto.class);
        if (student.getUser() != null){
            studentResponseDto.setUserName(student.getUser().getUserName());
            studentResponseDto.setRoleName(student.getUser().getRole().getRoleName());
        }
        if (!student.getCourses().isEmpty()){
            studentResponseDto.setCoursesName(student.getCourses().stream().map(Course::getCourseName).toList());
        }

        return studentResponseDto;
    }

    public List<StudentResponseDto> mapToDtoList(List<Student> students) {
        return students.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
