package com.example.demo.validations.user;

import com.example.demo.repositories.UserRepo;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<ValidUserId, Long> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        return userId != null && userService.hasUserExisted(userId);
    }
}
