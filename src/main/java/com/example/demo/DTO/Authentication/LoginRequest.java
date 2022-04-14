package com.example.demo.DTO.Authentication;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;

    private String password;
}
