package com.demo.Attendance.controller;

import com.demo.Attendance.dto.dtoAttendance.AttendanceRequestDto;
import com.demo.Attendance.dto.dtoAttendance.AttendanceResponseDto;
import com.demo.Attendance.serviceInterface.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/attendance")
    public ResponseEntity<AttendanceResponseDto> recordAttendance(@Valid @RequestBody AttendanceRequestDto attendanceRequestDto){
        return ResponseEntity.ok(attendanceService.markAttendance(attendanceRequestDto));
    }

    @GetMapping("/attendance")
    public List<AttendanceResponseDto> gatAllAttendance(){

        return attendanceService.getAttendanceData();
    }
}
