package org.example.springsecuritycrud.infrastruture.persistence.adapter;

import org.example.springsecuritycrud.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepositoryImpl extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}
