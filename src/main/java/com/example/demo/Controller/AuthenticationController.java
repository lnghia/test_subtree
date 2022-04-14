package com.example.demo.Controller;

import com.example.demo.CustomUserDetails.CustomUserDetails;
import com.example.demo.DTO.Authentication.LoginRequest;
import com.example.demo.DTO.Authentication.RegisterRequest;
import com.example.demo.Entity.User;
import com.example.demo.SecurityProvider.JWTAuth.JWTProvider;
import com.example.demo.Service.Authentication.AuthenticationService;
import com.example.demo.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JWTProvider jwtProvider;

    @GetMapping("/test")
    public String test(){
        return "test.";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        String username = request.getUsername();
        String password = request.getPassword();
        User user = authService.authenticateUser(username, password);

        if(user != null){
            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            String accessToken = jwtProvider.generateAccessToken(customUserDetails);

            return accessToken;
        }

        return "invalid credentials!";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        String email = request.getEmail();
        String username = request.getUsername();
        String password = request.getPassword();
        User user = userService.createUser(email, username, password);

        return "register successfully!";
    }
}
