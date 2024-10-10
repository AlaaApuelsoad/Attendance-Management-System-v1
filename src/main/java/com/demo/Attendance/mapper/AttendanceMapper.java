package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoAttendance.AttendanceResponseDto;
import com.demo.Attendance.model.Attendance;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceMapper {

    public static AttendanceResponseDto mapToAttendanceResponseDto(Attendance attendance) {

        AttendanceResponseDto attendanceResponseDto = new AttendanceResponseDto();

        attendanceResponseDto.setStudentId(attendance.getId());

        attendanceResponseDto.setStudentName(attendance.getStudent().getFirstName() + " " +
                attendance.getStudent().getLastName());

        attendanceResponseDto.setCourseName(attendance.getCourse().getCourseName());

        attendanceResponseDto.setStatus(attendance.getStatus());

        return attendanceResponseDto;
    }

    public static List<AttendanceResponseDto> attendanceResponseDtoList(List<Attendance> attendances) {
        return attendances.stream()
                .map(AttendanceMapper::mapToAttendanceResponseDto)
                .collect(Collectors.toList());
    }

}
