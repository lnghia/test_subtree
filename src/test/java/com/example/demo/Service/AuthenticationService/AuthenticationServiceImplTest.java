package com.example.demo.Service.AuthenticationService;


import com.example.demo.CustomUserDetails.CustomUserDetails;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepo;
import com.example.demo.SecurityProvider.JWTAuth.JWTProvider;
import com.example.demo.dto.Response.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    void beforeEach() {
        userRepo = mock(UserRepo.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtProvider = mock(JWTProvider.class);
        modelMapper = mock(ModelMapper.class);
        authService = new AuthenticationServiceImpl(userRepo, passwordEncoder, jwtProvider, modelMapper);
        initialUser = mock(UserEntity.class);
        expectedLoginResponseDTO = mock(LoginResponseDTO.class);
        expectedCustomUserDetails = CustomUserDetails.builder().user(initialUser).build();
        when(userRepo.findByUsername("alo")).thenReturn(Optional.of(initialUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.matches("alo", initialUser.getUsername())).thenReturn(true);
    }

    @Test
    void authenticateUser_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
        LoginResponseDTO result = authService.authenticateUser("alo", "alo");

        verify(jwtProvider).generateAccessToken(expectedCustomUserDetails);
        verify(jwtProvider).generateRefreshToken(expectedCustomUserDetails);
        assertThat(result.getRefreshToken(), is(expectedLoginResponseDTO.getRefreshToken()));
        assertThat(result.getAccessToken(), is(expectedLoginResponseDTO.getAccessToken()));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenPasswordInvalid() {
        LoginResponseDTO result = authService.authenticateUser("alo", "al");

        verify(jwtProvider, times(0)).generateAccessToken(expectedCustomUserDetails);
        verify(jwtProvider, times(0)).generateRefreshToken(expectedCustomUserDetails);
        assertThat(result, is(nullValue()));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenUsernameInvalid() {
        LoginResponseDTO result = authService.authenticateUser("alo", "al");

        verify(jwtProvider, times(0)).generateAccessToken(expectedCustomUserDetails);
        verify(jwtProvider, times(0)).generateRefreshToken(expectedCustomUserDetails);
        assertThat(result, is(nullValue()));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsInvalid() {
        LoginResponseDTO result = authService.authenticateUser("al", "al");

        verify(jwtProvider, times(0)).generateAccessToken(expectedCustomUserDetails);
        verify(jwtProvider, times(0)).generateRefreshToken(expectedCustomUserDetails);
        assertThat(result, is(nullValue()));
    }
}
