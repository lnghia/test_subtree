package com.example.demo.services.userrole;

import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.dto.responses.UserResponseDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private UserRepo userRepo;

    private RoleRepo roleRepo;

    private ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRepo userRepo, RoleRepo roleRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO assignRoleToUser(long userId, long roleId) {
        UserEntity userEntity = userRepo.getById(userId);
        RoleEntity roleEntity = roleRepo.getById(roleId);

        userEntity.getRoles().add(roleEntity);
        userEntity = userRepo.save(userEntity);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
