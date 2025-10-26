package org.example.springsecuritycrud.Infrastruture.controllers;

import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.RoleRepositoryImpl;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final UserRepositoryImpl UserRepositoryImpl;
    private final RoleRepositoryImpl RoleRepositoryImpl;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserRepositoryImpl userRepositoryImpl, RoleRepositoryImpl roleRepositoryImpl, PasswordEncoder passwordEncoder) {
        UserRepositoryImpl = userRepositoryImpl;
        RoleRepositoryImpl = roleRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public User createAdministrator(@RequestBody User newAdminUser) {
        Optional<Role> optionalRole = RoleRepositoryImpl.findByName((RoleEnum.SUPER_ADMIN_ROLE));

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setName(newAdminUser.getName());
        user.setEmail(newAdminUser.getEmail());
        user.setPassword(passwordEncoder.encode(newAdminUser.getPassword()));
        user.setRole(optionalRole.get());

        return UserRepositoryImpl.save(user);
    }
}