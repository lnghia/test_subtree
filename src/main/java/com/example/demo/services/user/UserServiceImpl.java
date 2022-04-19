package com.example.demo.services.user;

import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepo;
import com.example.demo.dto.responses.UserResponseDTO;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    @Override
    public Collection<RoleEntity> getUserGrantedPermissions(long id) {
        Optional<UserEntity> user = userRepo.findById(id);

        return user.get().getRoles();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public boolean hasUserExisted(long id) {
        Optional<UserEntity> user = userRepo.findById(id);

        return user.isPresent();
    }
}
