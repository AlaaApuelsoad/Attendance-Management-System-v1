package com.demo.Attendance.util;

import com.demo.Attendance.dtoCourse.CourseUpdateRequestDto;
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

    public void updateCourseDetails(Course course , CourseUpdateRequestDto courseUpdateRequestDto){

        if (isValid(courseUpdateRequestDto.getCourseName())){
            course.setCourseName(courseUpdateRequestDto.getCourseName());
        }
        if (isValid(courseUpdateRequestDto.getDescription())){
            course.setDescription(courseUpdateRequestDto.getDescription());
        }
    }

    private boolean isValid(String value){
        return value !=null && !value.trim().isEmpty();
    }
}
