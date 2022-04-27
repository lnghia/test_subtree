package com.example.demo.dto.requests;

import com.example.demo.validations.email.UniqueEmail;
import com.example.demo.validations.username.ValidUsername;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequestDTO {
    @NotNull
    @Email
    @UniqueEmail
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
