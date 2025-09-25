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
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final IRoleRepositoryImpl IRoleRepositoryImpl;
    private final IUserRepositoryImpl IUserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;

    public UserSeeder(
            IRoleRepositoryImpl IRoleRepositoryImpl,
            IUserRepositoryImpl IUserRepositoryImpl,
            PasswordEncoder passwordEncoder
    ) {
        this.IRoleRepositoryImpl = IRoleRepositoryImpl;
        this.IUserRepositoryImpl = IUserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createUser();
    }

    private void createUser() {
        User user = new User();
        user.setName("Andrey");
        user.setLastname("Acosta");
        user.setEmail("andrey@gmail.com");
        user.setPassword("andrey123");

        Optional<Role> optionalRole = IRoleRepositoryImpl.findByName(RoleEnum.USER);
        Optional<User> optionalUser = IUserRepositoryImpl.findByEmail(user.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(optionalRole.get());

        IUserRepositoryImpl.save(newUser);
    }
}
