package com.demo.Attendance.util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalTime;

@Component
public class AttendanceUtil {


    public String calculateStatus(Timestamp attendanceTime) {
        LocalTime time = attendanceTime.toLocalDateTime().toLocalTime();

        if (time.isBefore(LocalTime.of(21, 0))) {
            return "OnTime";
        }
        if (time.isAfter(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(21, 20))) {
            return "Late";
        }
        if (time.isAfter(LocalTime.of(21, 20))) {
            return "Absent";
        }
        return null;
    }
}
