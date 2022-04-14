package com.example.demo.Service.Authentication;

import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    User authenticateUser(String username, String password);
}
