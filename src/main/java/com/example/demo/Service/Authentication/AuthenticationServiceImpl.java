package com.example.demo.Service.Authentication;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticateUser(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
            return user.get();
        }

        return null;
    }
}
