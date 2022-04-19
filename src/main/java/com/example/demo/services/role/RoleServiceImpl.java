package com.example.demo.services.role;

import com.example.demo.entities.RoleEntity;
import com.example.demo.repositories.RoleRepo;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;

    private ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo, ModelMapper modelMapper) {
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        return roleRepo.save(roleEntity);
    }

    @Override
    public RoleEntity findByName(String name) {
//        String nameInUppercase = name.toUpperCase();
        Optional<RoleEntity> roleEntity = roleRepo.findByName(name);

        return roleEntity.orElse(null);
    }

    @Override
    public boolean hasRoleExisted(long id) {
        Optional<RoleEntity> roleEntity = roleRepo.findById(id);

        return roleEntity.isPresent();
    }
}
