package org.example.springsecuritycrud.application.seeders;

import org.example.springsecuritycrud.domain.entities.Role;
import org.example.springsecuritycrud.domain.enums.RoleEnum;
import org.example.springsecuritycrud.infrastruture.persistence.adapter.IRoleRepositoryImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final IRoleRepositoryImpl IRoleRepositoryImpl;


    public RoleSeeder(IRoleRepositoryImpl IRoleRepositoryImpl) {
        this.IRoleRepositoryImpl = IRoleRepositoryImpl;
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
            Optional<Role> optionalRole = IRoleRepositoryImpl.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                IRoleRepositoryImpl.save(roleToCreate);
            });
        });
    }
}