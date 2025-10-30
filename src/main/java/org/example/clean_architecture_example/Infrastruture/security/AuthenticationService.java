package org.example.clean_architecture_example.Infrastruture.security;

import org.example.clean_architecture_example.Domain.entities.User;
import org.example.clean_architecture_example.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepositoryImpl userRepositoryImpl;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepositoryImpl UserRepositoryImpl,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepositoryImpl = UserRepositoryImpl;
    }


    public User authenticate(User input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepositoryImpl.findByEmail(input.getEmail())
                .orElseThrow();
    }
}