package org.example.springsecuritycrud.Application.seeders;


import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.RoleRepositoryImpl;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.UserRepositoryImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepositoryImpl RoleRepositoryImpl;
    private final UserRepositoryImpl UserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepositoryImpl RoleRepositoryImpl,
            UserRepositoryImpl UserRepositoryImpl,
            PasswordEncoder passwordEncoder
    ) {
        this.RoleRepositoryImpl = RoleRepositoryImpl;
        this.UserRepositoryImpl = UserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        User superAdminRole = new User();
        superAdminRole.setName("Super");
        superAdminRole.setLastname("Admin");
        superAdminRole.setEmail("super.admin@gmail.com");
        superAdminRole.setPassword("superadmin123");

        Optional<Role> optionalRole = RoleRepositoryImpl.findByName((RoleEnum.SUPER_ADMIN_ROLE));
        Optional<User> optionalUser = UserRepositoryImpl.findByEmail(superAdminRole.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(superAdminRole.getName());
        user.setLastname(superAdminRole.getLastname());
        user.setEmail(superAdminRole.getEmail());
        user.setPassword(passwordEncoder.encode(superAdminRole.getPassword()));
        user.setRole(optionalRole.get());

        UserRepositoryImpl.save(user);
    }
}