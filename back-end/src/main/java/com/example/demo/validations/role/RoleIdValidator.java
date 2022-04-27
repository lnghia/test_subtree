package com.example.demo.validations.role;

import com.example.demo.entities.RoleEntity;
import com.example.demo.repositories.RoleRepo;
import com.example.demo.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class RoleIdValidator implements ConstraintValidator<ValidRoleId, Long> {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean isValid(Long roleId, ConstraintValidatorContext constraintValidatorContext) {
        return roleId != null && roleService.hasRoleExisted(roleId);
    }
}
