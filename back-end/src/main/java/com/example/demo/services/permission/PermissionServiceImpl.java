package com.example.demo.services.permission;

import com.example.demo.entities.PermissionEntity;
import com.example.demo.repositories.PermissionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService{
    private PermissionRepo permissionRepo;

    private ModelMapper modelMapper;

    @Autowired
    public PermissionServiceImpl(PermissionRepo permissionRepo, ModelMapper modelMapper){
        this.permissionRepo = permissionRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PermissionEntity save(PermissionEntity permissionEntity) {
        return permissionRepo.save(permissionEntity);
    }
}
