package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoCourse.CourseRequestDto;
import com.demo.Attendance.dto.dtoCourse.CourseResponseDto;
import com.demo.Attendance.mapper.CourseMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.repository.CourseRepository;
import com.demo.Attendance.serviceInterface.CourseService;
import com.demo.Attendance.util.CourseUtil;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;


@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseUtil courseUtil;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseUtil courseUtil, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseUtil = courseUtil;
        this.courseMapper = courseMapper;
    }

    @PostConstruct
    @Transactional
    public void saveCourses() {

        Faker faker = new Faker();

        List<CourseRequestDto> courseRequestDtoList = IntStream.range(0, 1) // generate 10 books
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

        Course course = courseMapper.matToCourse(courseRequestDto);
        courseRepository.save(course);
        return courseMapper.mapToDto(course);
    }

    @Override
    @Transactional
    public CourseResponseDto updateCourse(long id, CourseRequestDto courseRequestDto) {

        // Fetch course and throw if not found
        Course course = courseUtil.findCourseById(id);

        // update course details courseName , description
        courseUtil.updateCourseDetails(course, courseRequestDto);

        courseRepository.save(course);

        return courseMapper.mapToDto(course);
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
        return courseMapper.mapToDto(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        List<Course> courseList = courseRepository.findAll();
        return courseMapper.mapToDtoList(courseList);
    }

    @Override
    public Page<CourseResponseDto> searchByCourseName(String courseName, Pageable pageable) {

        Page<Course> coursePage = courseRepository.searchByCourseName(courseName, pageable);

        return coursePage.map(courseMapper::mapToDto);
    }



//    // Paginate courses with filtering by course name
//    public Page<CourseResponseDto> getCoursesByName(String name, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Course> coursePage = courseRepository.findByCourseNameContaining(name, pageable);
//
//        Page<CourseResponseDto> dtoPage = coursePage.map(courseMapper::mapToDto);
//        return dtoPage;
//    }


}
