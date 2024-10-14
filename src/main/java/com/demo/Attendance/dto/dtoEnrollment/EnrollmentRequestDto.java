package com.demo.Attendance.dto.dtoEnrollment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class EnrollmentRequestDto {

    @NotEmpty(message = "student id cannot be null")
    private Set<Long> studentId = new HashSet<>();

    @NotEmpty(message = "course id cannot be null")
    private Set<Long> courseId = new HashSet<>();


}
