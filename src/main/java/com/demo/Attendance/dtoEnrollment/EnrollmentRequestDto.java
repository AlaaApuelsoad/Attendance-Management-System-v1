package com.demo.Attendance.dtoEnrollment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EnrollmentRequestDto {

    @NotEmpty(message = "student id cannot be null")
    private List<Long> studentId = new ArrayList<>();

    @NotEmpty(message = "course id cannot be null")
    private List<Long> courseId = new ArrayList<>();


}
