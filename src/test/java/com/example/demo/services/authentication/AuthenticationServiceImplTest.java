package com.example.demo.services.authentication;


import com.example.demo.userdetails.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepo;
import com.example.demo.securityproviders.JWTProvider;
import com.example.demo.dto.responses.LoginResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AuthenticationServiceImplTest {
    UserEntity initialUser;
    AuthenticationService authService;
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ModelMapper modelMapper;
    LoginResponseDTO expectedLoginResponseDTO;
    CustomUserDetails expectedCustomUserDetails;

//    @BeforeEach
//    void beforeEach() {
//        userRepo = mock(UserRepo.class);
//        passwordEncoder = mock(PasswordEncoder.class);
//        jwtProvider = mock(JWTProvider.class);
//        modelMapper = mock(ModelMapper.class);
//        authService = new AuthenticationServiceImpl(userRepo, passwordEncoder, jwtProvider, modelMapper);
//        initialUser = UserEntity.builder().username("username").password("password").id(1L).build();
//        when(userRepo.findByUsername(not(eq()))).thenThrow(UserNotFoundException.class);
//        when(userRepo.findByUsername("username")).thenReturn(Optional.of(initialUser));
//        when(passwordEncoder.matches("password", initialUser.getPassword())).thenReturn(true);
//        when(passwordEncoder.matches(anyString(), anyString())).thenThrow(UsernamePasswordInvalidException.class);
//        when(jwtProvider.generateAccessToken(initialUser)).thenReturn("accessToken");
//        when(jwtProvider.generateRefreshToken(initialUser)).thenReturn("refreshToken");
//    }
//
//    @Test
//    void authenticateUser_ShouldReturnLoginResponseDTO_WhenCredentialsValid() {
//        LoginResponseDTO result = authService.authenticateUser("username", "password");
//
//        assertThat(result.getRefreshToken(), is("refreshToken"));
//        assertThat(result.getAccessToken(), is("accessToken"));
//    }

//    @Test
//    void authenticateUser_ShouldThrowUsernamePasswordInvalidException_WhenPasswordInvalid() {
//        LoginResponseDTO result = authService.authenticateUser("username", "invalidpassword");
//        UsernamePasswordInvalidException exception = assertThrows(UsernamePasswordInvalidException.class, () -> {
//
//        });
//        assertEquals("Invalid username or password", )
//    }
//
//    @Test
//    void authenticateUser_ShouldReturnNull_WhenUsernameInvalid() {
//        LoginResponseDTO result = authService.authenticateUser("alo", "al");
//
//        verify(jwtProvider, times(0)).generateAccessToken(expectedCustomUserDetails);
//        verify(jwtProvider, times(0)).generateRefreshToken(expectedCustomUserDetails);
//        assertThat(result, is(nullValue()));
//    }
//
//    @Test
//    void authenticateUser_ShouldReturnNull_WhenCredentialsInvalid() {
//        LoginResponseDTO result = authService.authenticateUser("al", "al");
//
//        verify(jwtProvider, times(0)).generateAccessToken(expectedCustomUserDetails);
//        verify(jwtProvider, times(0)).generateRefreshToken(expectedCustomUserDetails);
//        assertThat(result, is(nullValue()));
//    }
}
