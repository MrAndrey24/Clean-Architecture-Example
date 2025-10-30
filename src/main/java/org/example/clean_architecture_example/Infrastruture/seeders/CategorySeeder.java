package org.example.clean_architecture_example.Infrastruture.seeders;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Domain.repository.ICategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Order(1)
@Component
public class CategorySeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final ICategoryRepository repository;

    public CategorySeeder(ICategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createCategory();
    }


    @Transactional
    protected void createCategory() {
        List<Category> categories = List.of(
                new Category("Electronics", "Devices and gadgets"),
                new Category("Clothing", "Apparel and accessories"),
                new Category("Books", "Printed and digital books"),
                new Category("Accessories", "Various accessories for devices")
        );

        categories.stream().
                filter(category -> repository.findByName(Objects.requireNonNull(category.getName())).isEmpty())
                .forEach(repository::save);
    }
}
