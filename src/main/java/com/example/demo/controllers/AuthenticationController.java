package com.example.demo.controllers;

import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.RoleEntity;
import com.example.demo.services.permission.PermissionService;
import com.example.demo.services.role.RoleService;
import com.example.demo.services.userrole.UserRoleService;
import com.example.demo.dto.requests.AssignRoleToUserRequestDTO;
import com.example.demo.dto.requests.LoginRequestDTO;
import com.example.demo.dto.requests.RegisterRequestDTO;
import com.example.demo.entities.UserEntity;
import com.example.demo.securityproviders.JWTProvider;
import com.example.demo.services.authentication.AuthenticationService;
import com.example.demo.services.user.UserService;
import com.example.demo.dto.responses.LoginResponseDTO;
import com.example.demo.dto.responses.ResponseBodyDTO;
import com.example.demo.dto.responses.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/test")
    public String test() {
        return "test.";
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponseDTO loginResponseDTO = authService.authenticateUser(username, password);
        ResponseBodyDTO responseBodyDTO = ResponseBodyDTO.builder().data(loginResponseDTO).build();

        return ResponseEntity.ok(responseBodyDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBodyDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        UserEntity newUser = modelMapper.map(request, UserEntity.class);
        UserResponseDTO userResponseDTO = userService.createUser(newUser);
        ResponseBodyDTO response = ResponseBodyDTO.builder().data(userResponseDTO).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh_tokens")
    public ResponseEntity<ResponseBodyDTO> refreshTokens(@RequestHeader(value = "Refresh-Token", required = true) String refreshToken) {
        LoginResponseDTO responseDTO = authService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(ResponseBodyDTO.builder().data(responseDTO).build());
    }

    @GetMapping("/ttt")
    public ResponseEntity<ResponseBodyDTO> ttt() {
        ArrayList<RoleEntity> tmp = new ArrayList<>(userService.getUserGrantedPermissions(1L));

        return ResponseEntity.ok(ResponseBodyDTO.builder().data(tmp).build());
    }

    @PostMapping("/assign_role")
    public ResponseEntity<ResponseBodyDTO> assignRoleToUser(@Valid @RequestBody AssignRoleToUserRequestDTO requestBody) {
        UserResponseDTO userResponseDTO = userRoleService.assignRoleToUser(requestBody.getUserId(), requestBody.getRoleId());

        return ResponseEntity.ok(ResponseBodyDTO.builder().data(userResponseDTO).build());
    }

    @GetMapping("/iii")
    public ResponseEntity<ResponseBodyDTO> iii() {
        PermissionEntity permissionEntity = PermissionEntity.builder().value(2).name("write").build();
        PermissionEntity newPermission = permissionService.save(permissionEntity);
        List<PermissionEntity> tmp = new ArrayList<>(Arrays.asList(newPermission));
        RoleEntity roleEntity = RoleEntity.builder().name("user").value(2).permissions(tmp).build();
        RoleEntity newRoleEntity = roleService.save(roleEntity);

        return ResponseEntity.ok(new ResponseBodyDTO());
    }

    @GetMapping("/ooo")
    public ResponseEntity<ResponseBodyDTO> ooo() {
        RoleEntity roleEntity = roleService.findByName("user");
        UserEntity user = userService.getUserById(1L);
        user.getRoles().add(roleEntity);
        user = userService.save(user);

        return ResponseEntity.ok(ResponseBodyDTO.builder().data(user.getRoles()).build());
    }
}
