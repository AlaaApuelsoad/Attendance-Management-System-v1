package com.demo.Attendance.dto.AssignDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCourseAssignResponseDto {

    private List<String> instructorsName = new ArrayList<String>();
    private List<String> coursesName = new ArrayList<>();

}
