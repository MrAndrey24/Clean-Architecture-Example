package org.example.springsecuritycrud.infrastruture.controllers;

import org.example.springsecuritycrud.domain.entities.User;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {
    @Autowired
    private IUserRepositoryImpl IUserRepositoryImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN_ROLE')")
    public List<User> getAllUsers() {
        return IUserRepositoryImpl.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return IUserRepositoryImpl.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return IUserRepositoryImpl.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/{name}")
    public List<User> getUserById(@PathVariable String name) {
        return IUserRepositoryImpl.findUsersWithCharacterInName(name);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return IUserRepositoryImpl.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setLastname(user.getLastname());
                    existingUser.setEmail(user.getEmail());
                    return IUserRepositoryImpl.save(existingUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return IUserRepositoryImpl.save(user);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        IUserRepositoryImpl.deleteById(id);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}