package com.example.demo.Service.AuthenticationService;

import com.example.demo.Entity.UserEntity;
import com.example.demo.dto.Response.LoginResponseDTO;

public interface AuthenticationService {
    LoginResponseDTO authenticateUser(String username, String password);
}
