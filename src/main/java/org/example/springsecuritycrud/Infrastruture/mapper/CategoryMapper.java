package org.example.springsecuritycrud.Infrastruture.mapper;

import org.example.springsecuritycrud.Domain.entities.Category;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category entityToDomain(CategoryEntity entity);
    CategoryEntity domainToEntity(Category domain);
}
