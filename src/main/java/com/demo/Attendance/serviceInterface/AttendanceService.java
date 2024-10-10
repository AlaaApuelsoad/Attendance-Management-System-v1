package com.demo.Attendance.serviceInterface;


import com.demo.Attendance.dto.dtoAttendance.AttendanceRequestDto;
import com.demo.Attendance.dto.dtoAttendance.AttendanceResponseDto;

import java.util.List;

public interface AttendanceService {

    AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto);
    List<AttendanceResponseDto> getAttendanceData ();
}
