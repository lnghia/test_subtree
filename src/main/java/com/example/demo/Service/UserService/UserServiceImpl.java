package com.example.demo.Service.UserService;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
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
    public UserEntity createUser(String email, String username, String password) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepo.save(user);
    }
}
