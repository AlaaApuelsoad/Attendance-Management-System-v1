package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dtoEnrollment.EnrollmentRequestDto;
import com.demo.Attendance.dtoEnrollment.EnrollmentResponseDto;
import com.demo.Attendance.mapper.EnrollmentMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.repository.StudentRepository;
import com.demo.Attendance.util.CourseUtil;
import com.demo.Attendance.util.EnrollmentsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentsUtil enrollmentsUtil;
    private final CourseUtil courseUtil;

    public EnrollmentService(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentsUtil enrollmentsUtil, CourseUtil courseUtil) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentsUtil = enrollmentsUtil;
        this.courseUtil = courseUtil;
    }


    @Transactional
    public EnrollmentResponseDto enrollStudent(EnrollmentRequestDto enrollmentRequestDto) {

        // fetching list of studentIDS
        List<Student> students = enrollmentsUtil.findAllStudentsById(enrollmentRequestDto.getStudentId());

        if (students.size() != enrollmentRequestDto.getStudentId().size()) {
            enrollmentsUtil.filterStudentIDs(students,enrollmentRequestDto);
        }

        // fetching list of coursesIDS
        List<Course> courses = enrollmentsUtil.findAllCoursesById(enrollmentRequestDto.getCourseId());

        if (courses.size() != enrollmentRequestDto.getCourseId().size()) {
            enrollmentsUtil.filterCourseIDs(courses,enrollmentRequestDto);
        }

        for (Student student : students) {
            for (Course course : courses) {
                student.getCourses().add(course);
                course.getStudent().add(student);
            }
        }
        studentRepository.saveAll(students);
        courseRepository.saveAll(courses);
        return EnrollmentMapper.mapToEnrollResponseDto(students);
    }

    public EnrollmentResponseDto getStudentEnrollInCourse(long id){

        Course course = courseUtil.findCourseById(id);

        return EnrollmentMapper.mapToEnrollmentStudentInCourse(course);
    }
}
