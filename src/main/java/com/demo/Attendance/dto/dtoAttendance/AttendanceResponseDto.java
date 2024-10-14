package com.demo.Attendance.dto.dtoAttendance;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class AttendanceResponseDto {

    private long studentId;
    private String StudentName;
    private String courseName;
    private String status;

}
