package org.example.springsecuritycrud.application.seeders;


import org.example.springsecuritycrud.domain.entities.Role;
import org.example.springsecuritycrud.domain.entities.User;
import org.example.springsecuritycrud.domain.enums.RoleEnum;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IRoleRepositoryImpl;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IUserRepositoryImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final IRoleRepositoryImpl IRoleRepositoryImpl;
    private final IUserRepositoryImpl IUserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            IRoleRepositoryImpl IRoleRepositoryImpl,
            IUserRepositoryImpl IUserRepositoryImpl,
            PasswordEncoder passwordEncoder
    ) {
        this.IRoleRepositoryImpl = IRoleRepositoryImpl;
        this.IUserRepositoryImpl = IUserRepositoryImpl;
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

        Optional<Role> optionalRole = IRoleRepositoryImpl.findByName(RoleEnum.SUPER_ADMIN_ROLE);
        Optional<User> optionalUser = IUserRepositoryImpl.findByEmail(superAdminRole.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(superAdminRole.getName());
        user.setLastname(superAdminRole.getLastname());
        user.setEmail(superAdminRole.getEmail());
        user.setPassword(passwordEncoder.encode(superAdminRole.getPassword()));
        user.setRole(optionalRole.get());

        IUserRepositoryImpl.save(user);
    }
}