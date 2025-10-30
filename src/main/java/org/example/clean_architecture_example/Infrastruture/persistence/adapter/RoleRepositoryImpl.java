package org.example.clean_architecture_example.Infrastruture.persistence.adapter;

import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Domain.enums.RoleEnum;
import org.example.clean_architecture_example.Domain.repository.IRoleRepository;
import org.example.clean_architecture_example.Infrastruture.mapper.RoleMapper;
import org.example.clean_architecture_example.Infrastruture.persistence.repository.RoleRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements IRoleRepository {

    private final RoleRepositoryJpa repository;
    private final RoleMapper mapper;

    public RoleRepositoryImpl(RoleRepositoryJpa repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role save(Role role) {
        var entity = mapper.domainToEntity(role);
        var savedEntity = repository.save(entity);
        return mapper.entityToDomain(savedEntity);
    }

    @Override
    public Optional<Role> findByName(RoleEnum name) {
        return repository.findByName(name)
                .map(mapper::entityToDomain);
    }
}