package org.example.springsecuritycrud.Infrastruture.controllers;

import org.example.springsecuritycrud.Infrastruture.mapper.LoginResponse;
import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.RoleRepositoryImpl;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.example.springsecuritycrud.Application.service.AuthenticationService;
import org.example.springsecuritycrud.Application.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthRestController {

    private final UserRepositoryImpl UserRepositoryImpl;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepositoryImpl RoleRepositoryImpl;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthRestController(UserRepositoryImpl userRepositoryImpl, PasswordEncoder passwordEncoder, RoleRepositoryImpl roleRepositoryImpl, AuthenticationService authenticationService, JwtService jwtService) {
        UserRepositoryImpl = userRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
        RoleRepositoryImpl = roleRepositoryImpl;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        User authenticatedUser = authenticationService.authenticate(user);

        String jwtToken = jwtService.generateToken((UserDetails) authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        Optional<User> foundedUser = UserRepositoryImpl.findByEmail(user.getEmail());

        foundedUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> optionalRole = RoleRepositoryImpl.findByName((RoleEnum.USER));

        if (optionalRole.isEmpty()) {
            return null;
        }
        user.setRole(optionalRole.get());
        User savedUser = UserRepositoryImpl.save(user);
        return ResponseEntity.ok(savedUser);
    }

}