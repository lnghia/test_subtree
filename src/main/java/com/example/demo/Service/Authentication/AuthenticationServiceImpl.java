package com.example.demo.Service.Authentication;

import com.example.demo.Entity.User;
import com.example.demo.Service.User.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticateUser(String username, String password) {
        User user = userService.getUSerByUsername(username);

        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }

        return null;
    }
}
