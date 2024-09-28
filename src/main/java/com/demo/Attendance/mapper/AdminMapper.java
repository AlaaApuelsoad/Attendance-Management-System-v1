package com.demo.Attendance.mapper;

import com.demo.Attendance.dtoAdmin.AdminRequestDto;
import com.demo.Attendance.dtoAdmin.AdminResponseDto;
import com.demo.Attendance.model.Admin;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AdminMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    public Admin mapToAdmin(AdminRequestDto adminRequestDto) {

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper.convertValue(adminRequestDto, Admin.class);
    }

    public AdminResponseDto mapToDto(Admin admin) {

        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        AdminResponseDto adminResponseDto = objectMapper.convertValue(admin, AdminResponseDto.class);

        if (admin.getUser() != null) {
            adminResponseDto.setUserName(admin.getUser().getUserName());
            adminResponseDto.setRoleName(admin.getUser().getRole().getRoleName());
        }
        return adminResponseDto;
    }

    public List<AdminResponseDto> mapToDtolist(List<Admin> admins) {

        return admins.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


//    public static AdminResponseDto mapToDto(Admin admin){
//        objectMapper.registerModule(new SimpleModule().addDeserializer(AdminResponseDto.class, ));
//        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        return objectMapper.convertValue(admin,AdminResponseDto.class);
//    }

}
