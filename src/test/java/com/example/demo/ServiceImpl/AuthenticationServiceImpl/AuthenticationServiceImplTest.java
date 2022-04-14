package com.example.demo.ServiceImpl.AuthenticationServiceImpl;


import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.Authentication.AuthenticationService;
import com.example.demo.Service.Authentication.AuthenticationServiceImpl;
import com.example.demo.Service.User.UserService;
import com.example.demo.Service.User.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {
    User user;
    AuthenticationService authService;
    UserRepo userRepo;
    UserService userService;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach(){
        userService = mock(UserService.class);
        userRepo = mock(UserRepo.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthenticationServiceImpl(userRepo, passwordEncoder);
        // password is "alo" in plaintext
        user = User.builder().id(1L).email("abc@gmail.com").username("alo").password("$10$jg.egwWVynU8aBv6r8SKWOhFDiYnQavSSXNVwfxqYvstIJyhA7ByS").build();
        when(userRepo.findByUsername("alo")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), eq(user.getPassword()))).thenReturn(false);
        when(passwordEncoder.matches("alo", user.getPassword())).thenReturn(true);
    }

    @Test
    void authenticateUser_ShouldReturnUser_WhenCredentialsValid(){
        User result = authService.authenticateUser("alo", "alo");
        assertThat(result, is(user));
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenPasswordInvalid(){
        User result = authService.authenticateUser("alo", "al");
        assertThat(result, isNull());
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenUsernameInvalid(){
        User result = authService.authenticateUser("al", "alo");
        assertThat(result, isNull());
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenCredentialsInvalid(){
        User result = authService.authenticateUser("al", "al");
        assertThat(result, isNull());
    }
}
