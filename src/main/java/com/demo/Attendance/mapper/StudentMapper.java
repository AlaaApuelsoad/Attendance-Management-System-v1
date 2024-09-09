package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoStudent.StudentRequestDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;

import java.util.List;
import java.util.stream.Collectors;


public class StudentMapper {


    //Mapping StudentRequestDto to Student
    public static Student mapToStudent(StudentRequestDto studentRequestDto){

        Student student = new Student();

        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());

        return student;
    }

    public static StudentResponseDto mapToStudentResponseDto(Student student){

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setPhoneNumber(student.getPhoneNumber());
        studentResponseDto.setUserName(student.getUser().getUserName());

        //Handle if list of course is empty
        if (student.getCourses().isEmpty()){
            studentResponseDto.setCoursesName(null);
        }else {
            studentResponseDto.setCoursesName(student.getCourses().stream().map(Course::getCourseName).toList());
        }

        return studentResponseDto;
    }

    //Mapping List<Student> to List<StudentResponseDto>
    public static List<StudentResponseDto> toStudentResponseDTOList(List<Student> students) {
        return students.stream()
                .map(StudentMapper::mapToStudentResponseDto)
                .collect(Collectors.toList());
    }

}
