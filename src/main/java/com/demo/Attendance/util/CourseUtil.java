package com.demo.Attendance.util;

import com.demo.Attendance.dto.dtoCourse.CourseRequestDto;
import com.demo.Attendance.exceptionHandling.NotFoundException;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseUtil {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseUtil(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findCourseById(long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ConstantMessages.courseNotFound+id+ConstantMessages.notFoundMessage));
    }

    public void updateCourseDetails(Course course , CourseRequestDto courseRequestDto){

        if (isValid(courseRequestDto.getCourseName())){
            course.setCourseName(courseRequestDto.getCourseName());
        }
        if (isValid(courseRequestDto.getDescription())){
            course.setDescription(courseRequestDto.getDescription());
        }
    }

    private boolean isValid(String value){
        return value !=null && !value.trim().isEmpty();
    }
}
