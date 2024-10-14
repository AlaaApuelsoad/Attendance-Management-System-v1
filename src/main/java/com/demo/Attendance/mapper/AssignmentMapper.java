package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.AssignDto.InstructorCourseAssignResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AssignmentMapper {

    public InstructorCourseAssignResponseDto mapToDto(Set<Instructor> instructors) {

        InstructorCourseAssignResponseDto instructorCourseAssignResponseDto = new InstructorCourseAssignResponseDto();

        List<String> instructorsName = instructors.stream()
                .map(instructor -> instructor.getFirstName() + " " + instructor.getLastName())
                .toList();

        List<String> courseName = instructors.stream()
                .flatMap(instructor -> instructor.getCourses().stream())
                .map(Course::getCourseName)
                .distinct()
                .toList();


        instructorCourseAssignResponseDto.setInstructorsName(instructorsName);
        instructorCourseAssignResponseDto.setCoursesName(courseName);

        return instructorCourseAssignResponseDto;
    }

}
