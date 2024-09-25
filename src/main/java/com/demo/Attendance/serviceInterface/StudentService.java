package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentDto);

    StudentResponseDto updateStudent(long id, StudentRequestDto studentRequestDto);

    void deleteStudentById(long id);

    StudentResponseDto getStudentById(long id);

    List<StudentResponseDto> getAllStudents();

}
