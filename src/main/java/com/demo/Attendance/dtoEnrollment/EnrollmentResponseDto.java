package com.demo.Attendance.dtoEnrollment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
public class EnrollmentResponseDto {


    private String CourseName;

    private List<String> studentNameList = new ArrayList<>();

    private List<String> coursesNameList = new ArrayList<>();


}
