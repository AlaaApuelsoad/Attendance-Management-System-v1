package com.demo.Attendance.configuration;

import com.demo.Attendance.dto.dtoAdmin.AdminResponseDto;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomAdminResponseDtoDeserializer extends JsonDeserializer<AdminResponseDto> {


    @Override
    public AdminResponseDto deserialize(JsonParser p, DeserializationContext context) throws IOException, JacksonException {

        JsonNode node = p.getCodec().readTree(p);

        AdminResponseDto adminResponseDto = new AdminResponseDto();

        adminResponseDto.setId(node.get("id").asInt());
        adminResponseDto.setFirstName(node.get("firstName").asText());
        adminResponseDto.setLastName(node.get("lastName").asText());
        adminResponseDto.setEmail(node.get("email").asText());
        adminResponseDto.setPhoneNumber(node.get("phoneNumber").asText());

        return adminResponseDto;
    }
}
