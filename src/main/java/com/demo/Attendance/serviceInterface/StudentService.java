package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dto.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dto.dtoStudent.StudentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentDto);

    StudentResponseDto updateStudent(long id, StudentRequestDto studentRequestDto);

    void deleteStudentById(long id);

    StudentResponseDto getStudentById(long id);

    Page<StudentResponseDto> getAllStudents(Pageable pageable);

}
