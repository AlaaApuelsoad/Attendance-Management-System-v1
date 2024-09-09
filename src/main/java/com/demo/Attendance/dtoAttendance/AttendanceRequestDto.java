package com.demo.Attendance.dtoAttendance;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceRequestDto {

    @NotNull(message = "student id cannot be null")
    @Pattern(regexp = "^[1-9]\\d*$", message = "StudentID must be an integer greater than 0")
    private long studentId;

    @NotNull(message = "course id cannot be null")
    @Pattern(regexp = "^[1-9]\\d*$", message = "CourseID must be an integer greater than 0")
    private long courseId;

}
