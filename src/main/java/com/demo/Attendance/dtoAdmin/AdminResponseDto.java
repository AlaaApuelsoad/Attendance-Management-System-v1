package com.demo.Attendance.dtoAdmin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminResponseDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String role;

}
