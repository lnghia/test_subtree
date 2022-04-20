package com.example.demo.services.authentication;


import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.userdetails.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepo;
import com.example.demo.securityproviders.JWTProvider;
import com.example.demo.dto.responses.LoginResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {
    UserEntity initialUser;
    AuthenticationService authService;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ModelMapper modelMapper;
    LoginResponseDTO expectedLoginResponseDTO;
    CustomUserDetails expectedCustomUserDetails;

    @BeforeEach
    public void beforeEach() {
        userRepo = mock(UserRepo.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtProvider = mock(JWTProvider.class);
        modelMapper = mock(ModelMapper.class);
        authService = new AuthenticationServiceImpl(userRepo, passwordEncoder, jwtProvider, modelMapper);
        initialUser = UserEntity.builder().username("username").password("password").id(1L).build();
        when(userRepo.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepo.findByUsername("username")).thenReturn(Optional.of(initialUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.matches("password", initialUser.getPassword())).thenReturn(true);
        when(jwtProvider.generateAccessToken(initialUser)).thenReturn("accessToken");
        when(jwtProvider.generateRefreshToken(initialUser)).thenReturn("refreshToken");
    }

    @Test
    void authenticateUser_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
        LoginResponseDTO result = authService.authenticateUser("username", "password");

        assertThat(result.getRefreshToken(), is("refreshToken"));
        assertThat(result.getAccessToken(), is("accessToken"));
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenPasswordInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("username", "invalidpassword");
        });
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenUsernameInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("invalidusername", "password");
        });
    }

    @Test
    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenCredentialsInvalid() {
        assertThrows(UsernamePasswordInvalidException.class, () -> {
            authService.authenticateUser("invalidusername", "invalidpassword");
        });
    }
}
