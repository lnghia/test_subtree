package com.example.demo.Service.User;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(long id);
}
