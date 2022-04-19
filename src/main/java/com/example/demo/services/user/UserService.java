package com.example.demo.services.user;

import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.dto.responses.UserResponseDTO;

import java.util.Collection;

public interface UserService {
    UserEntity getUserById(long id);

    UserEntity getUSerByUsername(String username);

    UserResponseDTO createUser(UserEntity newUser);

    Collection<RoleEntity> getUserGrantedPermissions(long id);

    UserEntity save(UserEntity user);

    boolean hasUserExisted(long id);
}
