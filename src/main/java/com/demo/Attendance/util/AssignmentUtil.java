package com.demo.Attendance.util;

import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AssignmentUtil {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AssignmentUtil(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    public List<Instructor> findAllInstructorsById(List<Long> instructorIds) {
        return instructorRepository.findAllById(instructorIds);
    }

    public void filterInstructorsIds(Set<Instructor> instructors, InstructorCourseAssignRequestDto instructorCourseAssignRequestDto) {

        List<Long> instructorsFoundIds = instructors.stream().map(Instructor::getId).toList();
        List<Long> instructorsNotFoundIds = instructorCourseAssignRequestDto.getInstructorsId().stream()
                .filter(ids -> !instructorsFoundIds.contains(ids)).toList();

        throw new NotFoundException(ConstantMessages.INSTRUCTOR_WITH_ID +
                instructorsNotFoundIds + ConstantMessages.NOT_FOUND);
    }


    public List<Course> findAllCoursesById(List<Long> coursesIDs) {

        return courseRepository.findAllById(coursesIDs);
    }

    public void filterCourseIDs(Set<Course> courses, InstructorCourseAssignRequestDto instructorCourseAssignRequestDto) {

        List<Long> courseFoundIDs = courses.stream().map(Course::getId).toList();
        List<Long> courseNotFoundIDs = instructorCourseAssignRequestDto.getCoursesId().stream()
                .filter(id -> !courseFoundIDs.contains(id)).toList();

        throw new NotFoundException(ConstantMessages.COURSE_WITH_ID + courseNotFoundIDs + ConstantMessages.NOT_FOUND);
    }
}
