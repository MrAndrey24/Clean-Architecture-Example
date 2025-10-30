package org.example.clean_architecture_example.Infrastruture.persistence.repository;

import org.example.clean_architecture_example.Infrastruture.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Long> {
}
