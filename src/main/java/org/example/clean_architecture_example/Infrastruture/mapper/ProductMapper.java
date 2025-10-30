package org.example.clean_architecture_example.Infrastruture.mapper;

import org.example.clean_architecture_example.Domain.entities.Product;
import org.example.clean_architecture_example.Infrastruture.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product entityToDomain(ProductEntity entity);
    ProductEntity domainToEntity(Product product);
}
