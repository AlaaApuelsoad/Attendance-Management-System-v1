package com.demo.Attendance.dtoCourse;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseResponseDto {

    private long id;
    private String courseName;
    private String description;
    private String instructorName;


}
