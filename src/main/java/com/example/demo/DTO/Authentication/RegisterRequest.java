package com.example.demo.DTO.Authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class RegisterRequest {
    private String email;

    private String username;

    private String password;
}
