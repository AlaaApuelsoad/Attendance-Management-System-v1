package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoEnrollment.EnrollmentResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {

    public EnrollmentResponseDto mapToDto(Set<Student> students) {

        EnrollmentResponseDto enrollmentResponseDto = new EnrollmentResponseDto();

        // Collect student names
        List<String> studentNames = students.stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(Collectors.toList());

        // Collect course names for each student
        List<String> courseNames = students.stream()
                .flatMap(student -> student.getCourses().stream()) // Flatten list of courses from each student
                .map(Course::getCourseName) // Extract course names
                .distinct() // Ensure each course name is unique
                .collect(Collectors.toList());

        enrollmentResponseDto.setStudentNameList(studentNames);
        enrollmentResponseDto.setCoursesNameList(courseNames);

        return enrollmentResponseDto;

    }

    public EnrollmentResponseDto mapToDto(Course course) {

        EnrollmentResponseDto enrollmentResponseDto = new EnrollmentResponseDto();

        enrollmentResponseDto.setStudentNameList(course.getStudent().stream()
                .map(student -> student.getFirstName() + " " + student.getLastName()).toList());

        return enrollmentResponseDto;
    }
}
