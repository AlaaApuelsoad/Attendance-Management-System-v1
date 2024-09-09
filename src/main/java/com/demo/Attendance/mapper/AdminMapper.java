package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.model.Admin;

import java.util.List;
import java.util.stream.Collectors;

public class AdminMapper {

    public static Admin mapToAdmin(AdminRequestDto adminRequestDto){

        Admin admin = new Admin();

        admin.setFirstName(adminRequestDto.getFirstName());
        admin.setLastName(adminRequestDto.getLastName());
        admin.setEmail(adminRequestDto.getEmail());
        admin.setPhoneNumber(adminRequestDto.getPhoneNumber());

        return admin;
    }

    public static AdminResponseDto mapToAdminResponseDto(Admin admin){

        AdminResponseDto adminResponseDto = new AdminResponseDto();

        adminResponseDto.setId(admin.getId());
        adminResponseDto.setFirstName(admin.getFirstName());
        adminResponseDto.setLastName(admin.getLastName());
        adminResponseDto.setEmail(admin.getEmail());
        adminResponseDto.setPhoneNumber(admin.getPhoneNumber());
        adminResponseDto.setRole(admin.getUser().getRole().getRoleName());
        adminResponseDto.setUserName(admin.getUser().getUserName());

        return adminResponseDto;
    }

    public static List<AdminResponseDto> mapToAdminResponseList(List<Admin> admins){
        return admins.stream()
                .map(AdminMapper::mapToAdminResponseDto)
                .collect(Collectors.toList());
    }
}
