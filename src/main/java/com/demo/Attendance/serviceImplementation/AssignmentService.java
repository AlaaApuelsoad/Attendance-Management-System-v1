package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignRequestDto;
import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignResponseDto;
import com.demo.Attendance.mapper.AssignmentMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.repository.InstructorRepository;
import com.demo.Attendance.util.AssignmentUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AssignmentService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final AssignmentUtil assignmentUtil;
    private final AssignmentMapper assignmentMapper;


    public AssignmentService(InstructorRepository instructorRepository, CourseRepository courseRepository,
                             AssignmentUtil assignmentUtil, AssignmentMapper assignmentMapper) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.assignmentUtil = assignmentUtil;
        this.assignmentMapper = assignmentMapper;
    }

    @Transactional
    public InstructorCourseAssignResponseDto assignInstructorToCourse(InstructorCourseAssignRequestDto instructorCourseAssignRequestDto) {

        //validate coursesToAssign
        Set<Course> coursesToAssign = new HashSet<>(assignmentUtil.findAllCoursesById(instructorCourseAssignRequestDto.
                getCoursesId().stream().toList()));

        if (coursesToAssign.size() != instructorCourseAssignRequestDto.getCoursesId().size()) {
            assignmentUtil.filterCourseIDs(coursesToAssign, instructorCourseAssignRequestDto);
        }

        //validate instructors
        Set<Instructor> instructors = new HashSet<>(assignmentUtil.findAllInstructorsById(instructorCourseAssignRequestDto.
                getInstructorsId().stream().toList()));
        if (instructors.size() != instructorCourseAssignRequestDto.getInstructorsId().size()) {
            assignmentUtil.filterInstructorsIds(instructors, instructorCourseAssignRequestDto);
        }

        for (Instructor instructor : instructors) {
            instructor.getCourses().addAll(coursesToAssign);
        }

        instructorRepository.saveAll(instructors);
        return assignmentMapper.mapToDto(instructors);
    }
}
