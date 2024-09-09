package com.demo.Attendance.util;

import com.demo.Attendance.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnrollmentsUtil {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentsUtil(StudentRepository studentRepository,CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    public List<Student> findAllStudentsById(List<Long> studentsIDs){

        return studentRepository.findAllById(studentsIDs);
    }

    public void filterStudentIDs(List<Student> students,EnrollmentRequestDto enrollmentRequestDto){

        // Find which IDs are not found
        List<Long> studentFoundIDs = students.stream().map(Student::getId).toList();
        List<Long> studentNotFoundIDs = enrollmentRequestDto.getStudentId().stream()
                .filter(id -> !studentFoundIDs.contains(id)).toList();

        throw new NotFoundException(ConstantMessages.studentNotFound+studentNotFoundIDs+ConstantMessages.notFoundMessage);
    }

    public List<Course> findAllCoursesById(List<Long> coursesIDs){

        return courseRepository.findAllById(coursesIDs);
    }

    public void filterCourseIDs(List<Course> courses , EnrollmentRequestDto enrollmentRequestDto){

        //Find which IDs are not found
        List<Long> courseFoundIDs = courses.stream().map(Course::getId).toList();
        List<Long> courseNotFoundIDs = enrollmentRequestDto.getCourseId().stream()
                .filter(id -> !courseFoundIDs.contains(id)).toList();

        throw new NotFoundException(ConstantMessages.courseNotFound+courseNotFoundIDs+ConstantMessages.notFoundMessage);
    }
}
