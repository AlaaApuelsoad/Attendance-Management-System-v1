package com.demo.Attendance.dto.dtoInstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
public class InstructorResponseDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String RoleName;
    private List<String> coursesName = new ArrayList<>();


}
