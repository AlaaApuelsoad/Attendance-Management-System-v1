package com.demo.Attendance.dto.dtoEnrollment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EnrollmentResponseDto {

    private List<String> studentNameList = new ArrayList<>();
    private List<String> coursesNameList = new ArrayList<>();


}
