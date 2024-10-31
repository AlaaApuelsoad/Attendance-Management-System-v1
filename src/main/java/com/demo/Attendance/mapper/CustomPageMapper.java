package com.demo.Attendance.mapper;

import com.demo.Attendance.dto.dtoPage.CustomPageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CustomPageMapper {

    public static  <T> CustomPageDto<T> customPageDto(Page<T> page) {

        return new CustomPageDto<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
