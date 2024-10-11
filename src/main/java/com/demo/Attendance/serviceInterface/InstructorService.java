package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dto.dtoInstructor.InstructorRequestDto;
import com.demo.Attendance.dto.dtoInstructor.InstructorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstructorService {

    InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto);

    InstructorResponseDto updateInstructor(long id,InstructorRequestDto instructorRequestDto);

    void deleteInstructor(long id);

    InstructorResponseDto getInstructorById(long id);

    Page<InstructorResponseDto> getAllInstructor(Pageable pageable);

}
