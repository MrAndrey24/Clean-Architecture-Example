package org.example.clean_architecture_example.Infrastruture.mapper;

import org.example.clean_architecture_example.Domain.entities.Role;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role entityToDomain(RoleEntity entity);
    RoleEntity domainToEntity(Role role);

}
