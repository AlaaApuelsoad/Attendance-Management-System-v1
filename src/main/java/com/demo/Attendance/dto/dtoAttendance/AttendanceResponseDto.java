package com.demo.Attendance.dto.dtoAttendance;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttendanceResponseDto {

    private long studentId;
    private String StudentName;
    private String courseName;
    private String status;

}
