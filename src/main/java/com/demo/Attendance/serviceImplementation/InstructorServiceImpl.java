package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dto.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.mapper.InstructorMapper;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;
import com.demo.Attendance.model.User;
import com.demo.Attendance.repository.InstructorRepository;
import com.demo.Attendance.repository.UserRepository;
import com.demo.Attendance.serviceInterface.InstructorService;
import com.demo.Attendance.util.InstructorUtil;
import com.demo.Attendance.util.UniqueChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final UniqueChecker uniqueChecker;
    private final InstructorUtil instructorUtil;
    private final InstructorMapper instructorMapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, UserRepository userRepository,
                                 UniqueChecker uniqueChecker, InstructorUtil instructorUtil, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
        this.uniqueChecker = uniqueChecker;
        this.instructorUtil = instructorUtil;
        this.instructorMapper = instructorMapper;
    }


    @Override
    @Transactional
    public InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto){

        //Unique checker for email and phoneNumber
        uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());

        // set user for instructor
        User instructorUser = instructorUtil.setUserForInstructor(instructorRequestDto);

        Instructor instructor = instructorMapper.mapToInstructor(instructorRequestDto);
        instructor.setUser(instructorUser);

        instructorRepository.save(instructor);
        instructorUtil.updateUserNameWithId(instructor.getId(),instructorUser);
        userRepository.save(instructorUser);

        return instructorMapper.mapToDto(instructor);
    }

    @Override
    @Transactional
    public InstructorResponseDto updateInstructor(long id, InstructorRequestDto instructorRequestDto) {

        // Fetch instructor and throw if not found
        Instructor instructor = instructorUtil.findInstructorById(id);

        //Unique checker for email and phoneNumber
        uniqueChecker.emailUniqueChecker(instructorRequestDto.getEmail());
        uniqueChecker.phoneNumberUniqueChecker(instructorRequestDto.getPhoneNumber());

        // update courses for the instructor
        List<Course> courses = instructorUtil.validateCourse(instructorRequestDto.getCoursesId());

        // update courses for the instructor
        instructorUtil.updateInstructorCourse(instructor, courses);

        // update email, phone number and password if provided
        instructorUtil.updateInstructorDetails(instructor, instructorRequestDto);

        // save the updated instructor
        instructorRepository.save(instructor);

        return instructorMapper.mapToDto(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(long id) {

        Instructor instructor = instructorUtil.findInstructorById(id);
        instructorRepository.deleteById(id);
    }

    @Override
    public InstructorResponseDto getInstructorById(long id) {

        Instructor instructor = instructorUtil.findInstructorById(id);
        return instructorMapper.mapToDto(instructor);
    }

    @Override
    public List<InstructorResponseDto> getAllInstructor() {

        List<Instructor> instructors = instructorRepository.findAll();
        return instructorMapper.mapToDtoList(instructors);
    }


//    @Override
//    @Transactional
//    public InstructorResponseDto updateInstructor2(long id, InstructorUpdateRequestDto instructorUpdateRequestDto) {
//
//        Instructor instructor = instructorRepository.findById(id).orElseThrow(
//                () -> new NotFoundException("instructor with id - " + id + " not found")
//        );
//
//        List<CourseUtil> courses = courseRepository.findAllById(instructorUpdateRequestDto.getCoursesId());
//        if (courses.size() != instructorUpdateRequestDto.getCoursesId().size()) {
//
//            // Find which IDs are not found
//            List<Long> courseFoundIds = courses.stream().map(CourseUtil::getId).toList();
//            List<Long> courseNotFoundIDs = instructorUpdateRequestDto.getCoursesId().stream()
//                    .filter(courseId -> !courseFoundIds.contains(id)).toList();
//
//            System.out.println(courseNotFoundIDs);
//            throw new NotFoundException("CourseUtil not found with IDs: " + courseNotFoundIDs);
//        }
//
//        for (CourseUtil course : courses) {
//            instructor.getCourses().add(course);
//        }
//        //Unique checker
////        uniqueChecker.emailUniqueChecker(instructorUpdateRequestDto.getEmail());
////        uniqueChecker.phoneNumberUniqueChecker(instructorUpdateRequestDto.getPhoneNumber());
//
//        if (instructorUpdateRequestDto.getEmail() != null && !instructorUpdateRequestDto.getEmail().trim().isEmpty()) {
//            instructor.setEmail(instructorUpdateRequestDto.getEmail());
//        }
//        if (instructorUpdateRequestDto.getPhoneNumber() != null && !instructorUpdateRequestDto.getPhoneNumber().trim().isEmpty()) {
//            instructor.setPhoneNumber(instructorUpdateRequestDto.getPhoneNumber());
//        }
//        if (instructorUpdateRequestDto.getPassword() != null && !instructorUpdateRequestDto.getPassword().trim().isEmpty()) {
//            User instructorUser = instructor.getUser();
//            instructorUser.setPassword(passwordEncoder.encode(instructorUpdateRequestDto.getPassword()));
//            instructor.setUser(instructorUser);
//        }
//
//        instructorRepository.save(instructor);
//        return InstructorMapper.mapToInstructorResponse(instructor);
//    }

}
