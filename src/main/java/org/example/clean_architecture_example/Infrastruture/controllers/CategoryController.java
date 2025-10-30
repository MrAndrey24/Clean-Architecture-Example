package org.example.clean_architecture_example.Infrastruture.controllers;

import org.example.clean_architecture_example.Domain.entities.Category;
import org.example.clean_architecture_example.Domain.service.ICategoryService;
import org.example.clean_architecture_example.Infrastruture.mapper.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(service.getAllCategories()));
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> createCategory(@RequestBody Category newCategory) {
        try {
            service.createCategory(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Category created successfully"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        try {
            if (service.get(id) != null) {
                updatedCategory.setId(id);
                service.updateCategory(updatedCategory);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Category updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Category not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> deleteCategory(@PathVariable Long id) {
        try {
            if (service.get(id) != null) {
                service.deleteCategoryById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Category deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Category not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }
}
