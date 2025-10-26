package org.example.springsecuritycrud.Infrastruture.mapper;

import org.example.springsecuritycrud.Domain.entities.Role;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role entityToDomain(RoleEntity entity);
    RoleEntity domainToEntity(Role role);

}
