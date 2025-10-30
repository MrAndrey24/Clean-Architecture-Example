package org.example.clean_architecture_example.Infrastruture.seeders;


import jakarta.transaction.Transactional;
import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Domain.entities.User;
import org.example.clean_architecture_example.Domain.enums.RoleEnum;
import org.example.clean_architecture_example.Domain.repository.IRoleRepository;
import org.example.clean_architecture_example.Domain.repository.IUserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(IRoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    @Transactional
    protected void createSuperAdministrator() {
        User superAdminRole = new User();
        superAdminRole.setName("Super");
        superAdminRole.setLastname("Admin");
        superAdminRole.setEmail("super.admin@gmail.com");
        superAdminRole.setPassword("superadmin123");

        Optional<Role> optionalRole = roleRepository.findByName((RoleEnum.SUPER_ADMIN_ROLE));
        Optional<User> optionalUser = userRepository.findByEmail(superAdminRole.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(superAdminRole.getName());
        user.setLastname(superAdminRole.getLastname());
        user.setEmail(superAdminRole.getEmail());
        user.setPassword(passwordEncoder.encode(superAdminRole.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}