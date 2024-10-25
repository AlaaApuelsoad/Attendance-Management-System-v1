package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoRole.RoleRequestDto;
import com.demo.Attendance.dto.dtoRole.RoleResponseDto;
import com.demo.Attendance.model.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    private final ObjectMapper objectMapper;

    public RoleMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Role mapToRole(RoleRequestDto roleRequestDto) {
        return objectMapper.convertValue(roleRequestDto, Role.class);
    }

    public RoleResponseDto mapToDto(Role role){
        return objectMapper.convertValue(role, RoleResponseDto.class);
    }

    public List<RoleResponseDto> mapToDtoList(List<Role> roles) {
        return roles.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
