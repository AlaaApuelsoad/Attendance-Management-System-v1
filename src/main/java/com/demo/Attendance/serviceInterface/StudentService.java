package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.dtoStudent.StudentUpdateRequestDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentDto);

    StudentResponseDto updateStudent(long id, StudentUpdateRequestDto updateRequestDto);

    void deleteStudentById(long id);

    StudentResponseDto getStudentById(long id);

    List<StudentResponseDto> getAllStudents();

}
