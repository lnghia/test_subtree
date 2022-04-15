package com.example.demo.Service.AuthenticationService;

import com.example.demo.CustomUserDetails.CustomUserDetails;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepo;
import com.example.demo.SecurityProvider.JWTAuth.JWTProvider;
import com.example.demo.dto.Response.LoginResponseDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    private JWTProvider jwtProvider;

    private ModelMapper modelMapper;

    @Autowired
    public AuthenticationServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JWTProvider jwtProvider, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public LoginResponseDTO authenticateUser(String username, String password) {
        Optional<UserEntity> user = userRepo.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            CustomUserDetails customUserDetails = CustomUserDetails.builder().user(user.get()).build();
            String accessToken = jwtProvider.generateAccessToken(customUserDetails);
            String refreshToken = jwtProvider.generateRefreshToken(customUserDetails);
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(accessToken, refreshToken);

            return loginResponseDTO;
        }

        return null;
    }
}
