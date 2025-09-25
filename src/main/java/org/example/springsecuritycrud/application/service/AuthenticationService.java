package org.example.springsecuritycrud.application.service;

import org.example.springsecuritycrud.domain.entities.User;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IUserRepositoryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final IUserRepositoryImpl IUserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            IUserRepositoryImpl IUserRepositoryImpl,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.IUserRepositoryImpl = IUserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
    }


    public User authenticate(User input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return IUserRepositoryImpl.findByEmail(input.getEmail())
                .orElseThrow();
    }
}