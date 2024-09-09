package com.demo.Attendance.dtoRole;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleRequestDto {

    @NotEmpty(message = "role name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "role name must contain only letters")
    private List<String> roleName;

}
