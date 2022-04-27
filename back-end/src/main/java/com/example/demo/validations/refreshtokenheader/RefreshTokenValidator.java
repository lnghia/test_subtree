package com.example.demo.validations.refreshtokenheader;

import com.example.demo.securityproviders.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class RefreshTokenValidator implements ConstraintValidator<ValidRefreshToken, String> {
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public boolean isValid(String refreshToken, ConstraintValidatorContext constraintValidatorContext) {
        if(refreshToken != null && !refreshToken.isEmpty() && jwtProvider.validateToken(refreshToken)){
            return true;
        }

        return false;
    }
}
