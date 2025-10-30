package org.example.clean_architecture_example.Infrastruture.controllers;

import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Domain.entities.User;
import org.example.clean_architecture_example.Domain.enums.RoleEnum;
import org.example.clean_architecture_example.Infrastruture.mapper.LoginResponse;
import org.example.clean_architecture_example.Infrastruture.persistence.adapter.RoleRepositoryImpl;
import org.example.clean_architecture_example.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.example.clean_architecture_example.Infrastruture.persistence.repository.UserRepositoryJpa;
import org.example.clean_architecture_example.Infrastruture.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthRestController {

    private final UserRepositoryImpl userRepositoryImpl;
    private final UserRepositoryJpa userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepositoryImpl roleRepositoryImpl;
    private final JwtService jwtService;

    public AuthRestController(UserRepositoryImpl userRepositoryImpl, UserRepositoryJpa userRepository, PasswordEncoder passwordEncoder, RoleRepositoryImpl roleRepositoryImpl, JwtService jwtService) {
        this.userRepositoryImpl = userRepositoryImpl;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepositoryImpl = roleRepositoryImpl;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        try{
            var userEntity = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            String jwtToken = jwtService.generateToken((userEntity));
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            Optional<User> foundedUser = userRepositoryImpl.findByEmail(user.getEmail());

            foundedUser.ifPresent(loginResponse::setAuthUser);

            return ResponseEntity.ok(loginResponse);

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(new LoginResponse(e.getReason()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Error: " + e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> optionalRole = roleRepositoryImpl.findByName((RoleEnum.USER));

        if (optionalRole.isEmpty()) {
            return null;
        }
        user.setRole(optionalRole.get());
        User savedUser = userRepositoryImpl.save(user);
        return ResponseEntity.ok(savedUser);
    }

}