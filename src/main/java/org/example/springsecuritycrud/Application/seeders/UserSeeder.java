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
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepositoryImpl RoleRepositoryImpl;
    private final UserRepositoryImpl UserRepositoryImpl;

    private final PasswordEncoder passwordEncoder;

    public UserSeeder(
            RoleRepositoryImpl RoleRepositoryImpl,
            UserRepositoryImpl UserRepositoryImpl,
            PasswordEncoder passwordEncoder
    ) {
        this.RoleRepositoryImpl = RoleRepositoryImpl;
        this.UserRepositoryImpl = UserRepositoryImpl;
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

        Optional<Role> optionalRole = RoleRepositoryImpl.findByName((RoleEnum.USER));
        Optional<User> optionalUser = UserRepositoryImpl.findByEmail(user.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(optionalRole.get());

        UserRepositoryImpl.save(newUser);
    }
}
