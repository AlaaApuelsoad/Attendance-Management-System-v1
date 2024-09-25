package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto,long id);

    void deleteAdmin (long id);

    AdminResponseDto getAdminById(long id);

    List<AdminResponseDto> getAllAdmins();

}
