package com.example.demo.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;
}
