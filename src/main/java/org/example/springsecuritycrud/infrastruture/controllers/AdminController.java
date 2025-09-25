package org.example.springsecuritycrud.infrastruture.controllers;

import org.example.springsecuritycrud.domain.entities.Role;
import org.example.springsecuritycrud.domain.entities.User;
import org.example.springsecuritycrud.domain.enums.RoleEnum;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IRoleRepositoryImpl;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserRepositoryImpl IUserRepositoryImpl;

    @Autowired
    private IRoleRepositoryImpl IRoleRepositoryImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public User createAdministrator(@RequestBody User newAdminUser) {
        Optional<Role> optionalRole = IRoleRepositoryImpl.findByName(RoleEnum.SUPER_ADMIN_ROLE);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setName(newAdminUser.getName());
        user.setEmail(newAdminUser.getEmail());
        user.setPassword(passwordEncoder.encode(newAdminUser.getPassword()));
        user.setRole(optionalRole.get());

        return IUserRepositoryImpl.save(user);
    }
}