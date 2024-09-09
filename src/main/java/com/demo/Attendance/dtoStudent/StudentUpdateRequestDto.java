package com.demo.Attendance.dtoStudent;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentUpdateRequestDto {

    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Please enter a valid email" +
            " address in the format: username@example.com.")
    private String email;

    @Pattern(regexp = "^01[1205]\\d{8}$", message = "phone number must be 11 digits start with 01 and " +
            "have the third digit as either 0, 1, 2 or 5 ")
    private String phoneNumber;

    @Size(min = 6,max = 20,message = "password minimum length is 6 and maximum length is 20")
    private String password;

}
