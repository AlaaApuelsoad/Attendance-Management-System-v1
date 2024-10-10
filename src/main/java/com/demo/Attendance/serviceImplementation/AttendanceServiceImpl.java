package com.demo.Attendance.serviceImplementation;

import com.demo.Attendance.dto.dtoAttendance.AttendanceRequestDto;
import com.demo.Attendance.dto.dtoAttendance.AttendanceResponseDto;
import com.demo.Attendance.mapper.AttendanceMapper;
import com.demo.Attendance.model.Attendance;
import com.demo.Attendance.model.Course;
import com.demo.Attendance.model.Student;
import com.demo.Attendance.repository.AttendanceRepository;
import com.demo.Attendance.serviceInterface.AttendanceService;
import com.demo.Attendance.util.AttendanceUtil;
import com.demo.Attendance.util.CourseUtil;
import com.demo.Attendance.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceRepository attendanceRepository;
    private final StudentUtil studentUtil;
    private final CourseUtil courseUtil;
    private final AttendanceUtil attendanceUtil;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,StudentUtil studentUtil,
                                 CourseUtil courseUtil, AttendanceUtil attendanceUtil) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceUtil = attendanceUtil;
        this.studentUtil = studentUtil;
        this.courseUtil = courseUtil;
    }

    @Override
    @Transactional
    public AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto) {

        Student student = studentUtil.findStudentById(attendanceRequestDto.getStudentId());
        Course course = courseUtil.findCourseById(attendanceRequestDto.getCourseId());

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setStatus(attendanceUtil.calculateStatus(attendance.getAttendanceTime()));
        attendanceRepository.save(attendance);

        return AttendanceMapper.mapToAttendanceResponseDto(attendance);
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceData() {

        List<Attendance> attendances = attendanceRepository.findAll();
        return AttendanceMapper.attendanceResponseDtoList(attendances);
    }


}
