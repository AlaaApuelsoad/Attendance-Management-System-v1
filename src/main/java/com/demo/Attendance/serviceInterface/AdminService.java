package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto);

    AdminResponseDto updateAdmin(AdminRequestDto adminRequestDto,long id);

    void deleteAdmin (long id);

    AdminResponseDto getAdminById(long id);

    Page<AdminResponseDto> getAllAdmins(Pageable pageable);

    Page<AdminResponseDto> searchAdminByName(String firstName,Pageable pageable);

}
