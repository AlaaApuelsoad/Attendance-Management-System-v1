package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dtoCourse.CourseResponseDto;
import com.demo.Attendance.dtoCourse.CourseUpdateRequestDto;
import com.demo.Attendance.mapper.CourseMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.serviceInterface.CourseService;
import com.demo.Attendance.util.CourseUtil;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseUtil courseUtil;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseUtil courseUtil) {
        this.courseRepository = courseRepository;
        this.courseUtil = courseUtil;
    }

    @PostConstruct
    @Transactional
    public void saveCourses(){

        Faker faker = new Faker();

        List<CourseRequestDto> courseRequestDtoList = IntStream.range(0, 5) // generate 10 books
                .mapToObj(i -> new CourseRequestDto(
                        faker.educator().course(),
                        faker.lorem().sentence()
                ))
                .toList();

        courseRequestDtoList.forEach(this::createCourse);

    }

    @Override
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {

        Course course = CourseMapper.mapToCourse(courseRequestDto);
        courseRepository.save(course);
        return CourseMapper.mapToCourseUpdateResponseDto(course);
    }

    @Override
    @Transactional
    public CourseResponseDto updateCourse(long id, CourseUpdateRequestDto courseUpdateRequestDto) {

        // Fetch course and throw if not found
        Course course = courseUtil.findCourseById(id);

        // update course details courseName , description
        courseUtil.updateCourseDetails(course,courseUpdateRequestDto);

        courseRepository.save(course);

        return CourseMapper.mapToCourseUpdateResponseDto(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(long id) {

        Course course = courseUtil.findCourseById(id);
        courseRepository.deleteById(id);
    }


    @Override
    public CourseResponseDto getCourseById(long id) {

        Course course = courseUtil.findCourseById(id);
        return CourseMapper.mapToCourseUpdateResponseDto(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        List<Course> courseList = courseRepository.findAll();
        return CourseMapper.toCourseResponseDtoList(courseList);
    }

}
