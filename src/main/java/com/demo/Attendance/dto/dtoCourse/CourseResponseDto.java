package com.demo.Attendance.dto.dtoCourse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseDto {

    private long id;
    private String courseName;
    private String description;
    private List<String> instructorName;


}
