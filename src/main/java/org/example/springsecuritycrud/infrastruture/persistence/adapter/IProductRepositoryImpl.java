package org.example.springsecuritycrud.infrastruture.persistence.adapter;

import org.example.springsecuritycrud.domain.entities.Category;
import org.example.springsecuritycrud.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepositoryImpl extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
