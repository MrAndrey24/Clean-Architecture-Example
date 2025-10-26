package org.example.springsecuritycrud.Application.service;

import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepositoryImpl UserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepositoryImpl UserRepositoryImpl,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.UserRepositoryImpl = UserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
    }


    public User authenticate(User input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return UserRepositoryImpl.findByEmail(input.getEmail())
                .orElseThrow();
    }
}