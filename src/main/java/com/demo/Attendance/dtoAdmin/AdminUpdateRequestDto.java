package com.demo.Attendance.dtoAdmin;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminUpdateRequestDto {

    @Size(max = 50, message = "first name maximum char is 50 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "first name must contain only letters")
    private String firstName;

    @Size(max = 50, message = "last name maximum char is 50 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "last name must contain only letters")
    private String lastName;

    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Please enter a valid email" +
            " address in the format: username@example.com.")
    private String email;

    @Pattern(regexp = "^01[1205]\\d{8}$", message = "phone number must be 11 digits start with 01 and " +
            "have the third digit as either 0, 1, 2 or 5 ")
    private String phoneNumber;

    @Size (min = 6,max = 20,message = "password minimum length is 6 and maximum length is 20")
    private String password;

}
