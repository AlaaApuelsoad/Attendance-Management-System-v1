package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.dto.dtoEnrollment.EnrollmentResponseDto;
import com.demo.Attendance.mapper.EnrollmentMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.repository.StudentRepository;
import com.demo.Attendance.util.CourseUtil;
import com.demo.Attendance.util.EnrollmentsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class EnrollmentService {

    private final StudentRepository studentRepository;
    private final EnrollmentsUtil enrollmentsUtil;
    private final CourseUtil courseUtil;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(StudentRepository studentRepository, EnrollmentsUtil enrollmentsUtil, CourseUtil courseUtil, EnrollmentMapper enrollmentMapper) {
        this.studentRepository = studentRepository;
        this.enrollmentsUtil = enrollmentsUtil;
        this.courseUtil = courseUtil;
        this.enrollmentMapper = enrollmentMapper;
    }


    @Transactional
    public EnrollmentResponseDto enrollStudentToCourse(EnrollmentRequestDto enrollmentRequestDto) {

        Set<Student> students = new HashSet<>(enrollmentsUtil.findAllStudentsById(enrollmentRequestDto.getStudentId().stream().toList()));

        if (students.size() != enrollmentRequestDto.getStudentId().size()) {
            enrollmentsUtil.filterStudentIDs(students, enrollmentRequestDto);
        }

        Set<Course> coursesToAssign = new HashSet<>(enrollmentsUtil.findAllCoursesById(enrollmentRequestDto.getCourseId().stream().toList()));

        if (coursesToAssign.size() != enrollmentRequestDto.getCourseId().size()) {
            enrollmentsUtil.filterCourseIDs(coursesToAssign, enrollmentRequestDto);
        }

        for (Student student : students) {
            student.getCourses().addAll(coursesToAssign);
        }
        studentRepository.saveAll(students);
        return enrollmentMapper.mapToDto(students);
    }

    public EnrollmentResponseDto getStudentEnrollInCourse(long id) {
        Course course = courseUtil.findCourseById(id);
        return enrollmentMapper.mapToDto(course);
    }
}
