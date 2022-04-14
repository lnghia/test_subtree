package com.example.demo.Service.User;

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
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepo.findById(id);

        return user.orElse(null);
    }

    @Override
    public User getUSerByUsername(String username) {
        Optional<User> user = userRepo.findByUsername(username);

        return user.orElse(null);
    }

    @Override
    public User createUser(String email, String username, String password) {
        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepo.save(user);
    }
}
