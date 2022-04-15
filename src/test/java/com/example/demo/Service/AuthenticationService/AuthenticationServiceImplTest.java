package com.example.demo.Service.AuthenticationService;


import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {
    UserEntity user;
    AuthenticationService authService;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach() {
        userRepo = mock(UserRepo.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthenticationServiceImpl(userRepo, passwordEncoder);
        // password is "alo" in plaintext
        user = UserEntity.builder().id(1L).email("abc@gmail.com").username("alo").password("$10$jg.egwWVynU8aBv6r8SKWOhFDiYnQavSSXNVwfxqYvstIJyhA7ByS").build();
        when(userRepo.findByUsername("alo")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), eq(user.getPassword()))).thenReturn(false);
        when(passwordEncoder.matches("alo", user.getPassword())).thenReturn(true);
    }

    @Test
    void authenticateUser_ShouldReturnUser_WhenCredentialsValid() {
        UserEntity result = authService.authenticateUser("alo", "alo");
        assertThat(result, is(user));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenPasswordInvalid() {
        UserEntity result = authService.authenticateUser("alo", "al");
        assertThat(result, is(nullValue()));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenUsernameInvalid() {
        UserEntity result = authService.authenticateUser("al", "alo");
        assertThat(result, is(nullValue()));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsInvalid() {
        UserEntity result = authService.authenticateUser("al", "al");
        assertThat(result, is(nullValue()));
    }
}
