package com.example.demo.services.userrole;

import com.example.demo.dto.responses.UserResponseDTO;

public interface UserRoleService {
    UserResponseDTO assignRoleToUser(long userId, long roleId);
}
