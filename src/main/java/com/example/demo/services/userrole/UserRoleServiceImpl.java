package com.example.demo.services.userrole;

import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.repositories.UserRepo;
import com.example.demo.dto.responses.UserResponseDTO;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        UserEntity user = userEntity.get();
        RoleEntity roleEntity = roleRepo.getById(roleId);

        user.getRoles().add(roleEntity);
        user = userRepo.save(user);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
