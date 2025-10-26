package org.example.springsecuritycrud.Infrastruture.persistence.adapter;

import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Domain.enums.RoleEnum;
import org.example.springsecuritycrud.Domain.repository.IRoleRepository;
import org.example.springsecuritycrud.Infrastruture.mapper.RoleMapper;
import org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData.SpringRoleRepository;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements IRoleRepository {

    private final SpringRoleRepository repository;
    private final RoleMapper mapper;

    public RoleRepositoryImpl(SpringRoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role save(Role role) {
        RoleEntity entity = mapper.domainToEntity(role);
        return  mapper.entityToDomain(repository.save(entity));
    }

    @Override
    public Optional<Role> findByName(RoleEnum name) {
        return repository.findByName(name)
                .map(mapper::entityToDomain);
    }
}