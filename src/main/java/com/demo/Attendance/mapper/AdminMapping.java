package com.demo.Attendance.mapper;

import com.demo.Attendance.configuration.CustomAdminResponseDtoDeserializer;
import com.demo.Attendance.dto.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.model.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class AdminMapping {

    private final ObjectMapper objectMapper;

    public AdminMapping(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Admin mapToAdmin(AdminRequestDto adminRequestDto) {

        System.out.println(objectMapper);
        return objectMapper.convertValue(adminRequestDto, Admin.class);
    }

    public AdminResponseDto mapToDto(Admin admin) {

//        AdminResponseDto adminResponseDto = new AdminResponseDto();
//        adminResponseDto.setRoleName(admin.getUser().getRole().getRoleName());
//        adminResponseDto.setUserName(admin.getUser().getUserName());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(AdminResponseDto.class,new CustomAdminResponseDtoDeserializer());
        objectMapper.registerModule(module);

        return objectMapper.convertValue(admin, AdminResponseDto.class);
    }
}
