package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.model.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class AdminMappingTest {

    private final ObjectMapper objectMapper;

    public AdminMappingTest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Admin mapToAdmin(AdminRequestDto adminRequestDto) {

        System.out.println(objectMapper);
        return objectMapper.convertValue(adminRequestDto, Admin.class);
    }

    public AdminResponseDto mapToDto(Admin admin) {

        SimpleModule module = new SimpleModule();
        module.addDeserializer(AdminResponseDto.class,new CustomAdminResponseDtoDeserializer());
        objectMapper.registerModule(module);

        return objectMapper.convertValue(admin, AdminResponseDto.class);
    }
}
