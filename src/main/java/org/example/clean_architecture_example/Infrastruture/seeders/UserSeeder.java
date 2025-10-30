package org.example.clean_architecture_example.Infrastruture.seeders;

import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Domain.entities.User;
import org.example.clean_architecture_example.Domain.enums.RoleEnum;
import org.example.clean_architecture_example.Domain.repository.IRoleRepository;
import org.example.clean_architecture_example.Domain.repository.IUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(IRoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createUser();
    }

    @Transactional
    protected void createUser() {
        User user = new User();
        user.setName("Andrey");
        user.setLastname("Acosta");
        user.setEmail("andrey@gmail.com");
        user.setPassword("andrey123");

        Optional<Role> optionalRole = roleRepository.findByName((RoleEnum.USER));
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(optionalRole.get());

        userRepository.save(newUser);
    }
}
