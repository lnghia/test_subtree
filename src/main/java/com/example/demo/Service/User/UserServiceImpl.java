package com.example.demo.Service.User;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.Optional;

@NoArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepo.findById(id);

        return user.orElse(null);
    }
}
