package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.dtoAdmin.AdminUpdateRequestDto;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdmin(AdminUpdateRequestDto adminUpdateRequestDto,long id);

    void deleteAdmin (long id);

    AdminResponseDto getAdminById(long id);

    List<AdminResponseDto> getAllAdmins();

}
