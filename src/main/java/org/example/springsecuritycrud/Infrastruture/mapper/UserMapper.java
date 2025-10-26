package org.example.springsecuritycrud.Infrastruture.mapper;

import org.example.springsecuritycrud.Domain.entities.User;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User user);

}
