package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dtoInstructor.InstructorResponseDto;
import com.demo.Attendance.dtoInstructor.InstructorUpdateRequestDto;

import java.util.List;

public interface InstructorService {

    InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto);

    InstructorResponseDto updateInstructor(long id,InstructorUpdateRequestDto instructorUpdateRequestDto);

    void deleteInstructor(long id);

    InstructorResponseDto getInstructorById(long id);

    List<InstructorResponseDto> getAllInstructor();


//    InstructorResponseDto updateInstructor2(long id, InstructorUpdateRequestDto instructorUpdateRequestDto);

}
