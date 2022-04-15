package com.example.demo.Service.AuthenticationService;

import com.example.demo.Entity.UserEntity;

public interface AuthenticationService {
    UserEntity authenticateUser(String username, String password);
}
