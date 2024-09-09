package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Instructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorMapper {

    //Mapping to Instructor
    public static Instructor mapToInstructor(InstructorRequestDto instructorRequestDto){

       Instructor instructor = new Instructor();

       instructor.setFirstName(instructorRequestDto.getFirstName());
       instructor.setLastName(instructorRequestDto.getLastName());
       instructor.setEmail(instructorRequestDto.getEmail());
       instructor.setPhoneNumber(instructorRequestDto.getPhoneNumber());

       return instructor;
    }

    //Mapping to InstructorResponseDto for create
    public static InstructorResponseDto mapToInstructorResponse(Instructor instructor){

        InstructorResponseDto instructorResponseDto = new InstructorResponseDto();

        instructorResponseDto.setId(instructor.getId());
        instructorResponseDto.setFirstName(instructor.getFirstName());
        instructorResponseDto.setLastName(instructor.getLastName());
        instructorResponseDto.setEmail(instructor.getEmail());
        instructorResponseDto.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponseDto.setUserName(instructor.getUser().getUserName());

        if (instructor.getCourses() != null && !instructor.getCourses().isEmpty()) {
            instructorResponseDto.setCoursesName(
                    instructor.getCourses().stream().map(Course::getCourseName).toList()
            );
        } else {
            instructorResponseDto.setCoursesName(Collections.emptyList()); // or null if you prefer
        }


        return instructorResponseDto;
    }
//    public static InstructorResponseDto mapToInstructorResponseUpdate(Instructor instructor){
//
//        InstructorResponseDto instructorResponseDto = new InstructorResponseDto();
//
//        instructorResponseDto.setId(instructor.getId());
//        instructorResponseDto.setFirstName(instructor.getFirstName());
//        instructorResponseDto.setLastName(instructor.getLastName());
//        instructorResponseDto.setEmail(instructor.getEmail());
//        instructorResponseDto.setPhoneNumber(instructor.getPhoneNumber());
//        instructorResponseDto.setUserName(instructor.getUser().getUserName());
//        if (!instructor.getCourses().isEmpty()){
//            instructorResponseDto.setCoursesName(instructor.getCourses().stream().map(CourseUtil::getCourseName).toList());
//        }
//
//        return instructorResponseDto;
//    }

    //Mapping List<Instructor> to List<InstructorResponseDto>
    public static List<InstructorResponseDto> toInstructorResponseDTOList(List<Instructor> instructors) {
        return instructors.stream()
                .map(InstructorMapper::mapToInstructorResponse)
                .collect(Collectors.toList());
    }

}
