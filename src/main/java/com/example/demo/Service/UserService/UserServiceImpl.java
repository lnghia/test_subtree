package com.example.demo.Service.UserService;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepo;
import com.example.demo.dto.Request.RegisterRequestDTO;
import com.example.demo.dto.Response.UserResponseDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity getUserById(long id) {
        Optional<UserEntity> user = userRepo.findById(id);

        return user.orElse(null);
    }

    @Override
    public UserEntity getUSerByUsername(String username) {
        Optional<UserEntity> user = userRepo.findByUsername(username);

        return user.orElse(null);
    }

    @Override
    public UserResponseDTO createUser(UserEntity newUser) {
        String plainPassword = newUser.getPassword();
        String encryptedPassword = passwordEncoder.encode(plainPassword);

        newUser.setPassword(encryptedPassword);

        return modelMapper.map(userRepo.save(newUser), UserResponseDTO.class);
    }
}
