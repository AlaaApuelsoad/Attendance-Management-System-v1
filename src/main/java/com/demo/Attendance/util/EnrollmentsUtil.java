package com.demo.Attendance.util;

import com.demo.Attendance.dto.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    public void filterStudentIDs(Set<Student> students, EnrollmentRequestDto enrollmentRequestDto){

        List<Long> studentFoundIDs = students.stream().map(Student::getId).toList();
        List<Long> studentNotFoundIDs = enrollmentRequestDto.getStudentId().stream()
                .filter(id -> !studentFoundIDs.contains(id)).toList();

        throw new NotFoundException(ConstantMessages.STUDENT_WITH_ID +studentNotFoundIDs+ConstantMessages.NOT_FOUND);
    }

    public List<Course> findAllCoursesById(List<Long> coursesIDs){

        return courseRepository.findAllById(coursesIDs);
    }

    public void filterCourseIDs(Set<Course> courses , EnrollmentRequestDto enrollmentRequestDto){

        List<Long> courseFoundIDs = courses.stream().map(Course::getId).toList();
        List<Long> courseNotFoundIDs = enrollmentRequestDto.getCourseId().stream()
                .filter(id -> !courseFoundIDs.contains(id)).toList();

        throw new NotFoundException(ConstantMessages.COURSE_WITH_ID +courseNotFoundIDs+ConstantMessages.NOT_FOUND);
    }
}
