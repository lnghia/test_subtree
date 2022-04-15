package com.example.demo.Service.UserService;

import com.example.demo.Entity.UserEntity;

public interface UserService {
    UserEntity getUserById(long id);

    UserEntity getUSerByUsername(String username);

    UserEntity createUser(String email, String username, String password);
}
