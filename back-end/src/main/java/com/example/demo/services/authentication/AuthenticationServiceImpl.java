package com.example.demo.services.authentication;

import com.example.demo.dto.responses.LoginResponseDTO;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.exceptions.UsernamePasswordInvalidException;
import com.example.demo.repositories.UserRepo;
import com.example.demo.securityproviders.JWTProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@Getter
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
            String accessToken = jwtProvider.generateAccessToken(user.get());
            String refreshToken = jwtProvider.generateRefreshToken(user.get());
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(accessToken, refreshToken);

            return loginResponseDTO;
        }

        throw new UsernamePasswordInvalidException();
    }

    @Override
    public LoginResponseDTO refreshAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            long userId = jwtProvider.getUserIdFromJWT(refreshToken);
            Optional<UserEntity> user = userRepo.findById(userId);

            if (user.isPresent()) {
                String newAccessToken = jwtProvider.generateAccessToken(user.get());
                String newRefreshToken = jwtProvider.generateRefreshToken(user.get());

                return LoginResponseDTO.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
            }
        }

        throw new InvalidTokenException();
    }
}
