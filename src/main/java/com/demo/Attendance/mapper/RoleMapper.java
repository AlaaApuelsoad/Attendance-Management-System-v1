package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoRole.RoleResponseDto;
import com.demo.Attendance.dtoStudent.StudentResponseDto;
import com.demo.Attendance.model.Role;
import com.demo.Attendance.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleResponseDto mapToRoleResponseDto(Role role){

        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId(role.getId());

        roleResponseDto.setRoleName(role.getRoleName());

        return roleResponseDto;
    }

    //Mapping List<Role> to List<RoleResponseDto>
    public static List<RoleResponseDto> toRoleResponseDtoList(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::mapToRoleResponseDto)
                .collect(Collectors.toList());
    }
}
