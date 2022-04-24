package com.example.demo.validations.email;

import com.example.demo.repositories.UserRepo;
import com.example.demo.services.user.UserService;
import com.example.demo.validations.refreshtokenheader.ValidRefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.hasUserExisted(email);
    }
}
