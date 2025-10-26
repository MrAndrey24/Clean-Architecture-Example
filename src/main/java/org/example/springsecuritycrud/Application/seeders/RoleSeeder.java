package org.example.springsecuritycrud.Application.seeders;

import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.RoleRepositoryImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepositoryImpl RoleRepositoryImpl;


    public RoleSeeder(RoleRepositoryImpl RoleRepositoryImpl) {
        this.RoleRepositoryImpl = RoleRepositoryImpl;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.SUPER_ADMIN_ROLE };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.SUPER_ADMIN_ROLE, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = RoleRepositoryImpl.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                RoleRepositoryImpl.save(roleToCreate);
            });
        });
    }
}