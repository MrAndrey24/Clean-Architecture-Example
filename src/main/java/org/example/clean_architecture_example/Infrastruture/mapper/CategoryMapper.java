package org.example.clean_architecture_example.Infrastruture.mapper;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products", ignore = true)
    Category entityToDomain(CategoryEntity entity);
    @Mapping(target = "products", ignore = true)
    CategoryEntity domainToEntity(Category domain);
}
