package com.example.demo.Service.UserService;

import com.example.demo.Entity.UserEntity;
import com.example.demo.dto.Request.RegisterRequestDTO;
import com.example.demo.dto.Response.UserResponseDTO;

public interface UserService {
    UserEntity getUserById(long id);

    UserEntity getUSerByUsername(String username);

    UserResponseDTO createUser(UserEntity newUser);
}
