package org.example.springsecuritycrud.Infrastruture.mapper;

import org.example.springsecuritycrud.Domain.entities.Product;
import org.example.springsecuritycrud.Infrastruture.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product entityToDomain(ProductEntity entity);
    ProductEntity domainToEntity(Product product);
}
