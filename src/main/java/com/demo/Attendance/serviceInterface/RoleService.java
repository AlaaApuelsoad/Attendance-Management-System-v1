package com.demo.Attendance.serviceInterface;

import com.demo.Attendance.dtoRole.RoleRequestDto;
import com.demo.Attendance.dtoRole.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> createRole(RoleRequestDto roleRequestDto);
    RoleResponseDto getRoleById(long id);

    List<RoleResponseDto> getAllRoles();

    void deleteRoleById(long id);


}
