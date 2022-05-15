package com.hobby.blogapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    @NotEmpty
    @Size(min = 4, message = "UserEntity must be min of 4 character")
    private String name;
    @Email(message = "email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 8, max = 20, message = "Password must be between min 8 and max 20 character")
    private String password;
    @NotEmpty
    private String about;
}
