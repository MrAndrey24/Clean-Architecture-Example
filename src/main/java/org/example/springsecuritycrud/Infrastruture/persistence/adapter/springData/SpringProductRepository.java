package org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData;

import org.example.springsecuritycrud.Infrastruture.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringProductRepository extends JpaRepository<ProductEntity, Long> {
}
