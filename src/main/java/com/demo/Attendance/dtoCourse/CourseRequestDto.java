package com.demo.Attendance.dtoCourse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseRequestDto {

    @NotEmpty(message = "CourseUtil name cannot be null")
    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "course name must contain only letters")
    private String courseName;

    @NotEmpty(message = "course description cannot be null")
    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "description must contain only letters")
    private  String description;

    public CourseRequestDto(String course, String sentence) {

        this.courseName = course;
        this.description = sentence;
    }
}
