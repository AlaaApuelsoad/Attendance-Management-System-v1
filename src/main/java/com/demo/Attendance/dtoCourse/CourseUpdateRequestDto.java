package com.demo.Attendance.dtoCourse;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseUpdateRequestDto {

    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "course name must contain only letters")
    private String courseName;

    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "description must contain only letters")
    private String description;


}
