package com.example.demo.Service.User;

import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User getUserById(long id);
    User getUSerByUsername(String username);
    User createUser(String email, String username, String password);
}
