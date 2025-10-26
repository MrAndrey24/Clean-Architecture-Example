package org.example.springsecuritycrud.Infrastruture.persistence.adapter.springData;

import org.example.springsecuritycrud.Infrastruture.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
