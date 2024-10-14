package com.demo.Attendance.dto.AssignDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCourseAssignRequestDto {

    @NotEmpty(message = "instructor id cannot be null")
    private Set<Long> instructorsId = new HashSet<>();

    @NotEmpty(message = "course id cannot be null")
    private Set<Long> coursesId = new HashSet<>();
}
