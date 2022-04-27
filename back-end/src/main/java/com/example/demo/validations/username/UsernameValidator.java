package com.example.demo.validations.username;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<UserEntity> user = userRepo.findByUsername(username);

        return !user.isPresent();
    }
}
