package com.demo.Attendance.dtoCourse;

import com.demo.Attendance.serviceInterface.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "CourseUtil name cannot be null",groups = OnCreate.class)
    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "course name must contain only letters")
    private String courseName;

    @NotBlank(message = "course description cannot be null",groups = OnCreate.class)
    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]+$", message = "description must contain only letters")
    private  String description;

}
