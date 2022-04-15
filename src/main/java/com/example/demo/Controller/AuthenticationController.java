package com.example.demo.Controller;

import com.example.demo.CustomUserDetails.CustomUserDetails;
import com.example.demo.Exception.UsernamePasswordInvalidException;
import com.example.demo.dto.Request.LoginRequestDTO;
import com.example.demo.dto.Request.RegisterRequestDTO;
import com.example.demo.Entity.UserEntity;
import com.example.demo.SecurityProvider.JWTAuth.JWTProvider;
import com.example.demo.Service.AuthenticationService.AuthenticationService;
import com.example.demo.Service.UserService.UserService;
import com.example.demo.dto.Response.LoginResponseDTO;
import com.example.demo.dto.Response.ResponseBodyDTO;
import com.example.demo.dto.Response.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/test")
    public String test() {
        return "test.";
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDTO> login(@RequestBody LoginRequestDTO request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponseDTO loginResponseDTO = authService.authenticateUser(username, password);

        if(loginResponseDTO != null){
            ResponseBodyDTO responseBodyDTO = ResponseBodyDTO.builder().data(loginResponseDTO).build();

            return ResponseEntity.ok(responseBodyDTO);
        }

        throw new UsernamePasswordInvalidException();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBodyDTO> register(@RequestBody RegisterRequestDTO request) {
        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        UserResponseDTO userResponseDTO = userService.createUser(newUser);
        ResponseBodyDTO response = ResponseBodyDTO.builder().data(userResponseDTO).build();

        return ResponseEntity.ok(response);
    }
}
