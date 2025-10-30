package org.example.clean_architecture_example.Infrastruture.controllers;

import org.example.clean_architecture_example.Domain.entities.Product;
import org.example.clean_architecture_example.Domain.service.IProductService;
import org.example.clean_architecture_example.Infrastruture.mapper.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse(service.getAllProducts()));
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> createProduct(@RequestBody Product newProduct) {
        try {
            service.createProduct(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Product created successfully"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            if (service.get(id) != null) {
                updatedProduct.setId(id);
                service.updateProduct(updatedProduct);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Product updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Product not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN_ROLE')")
    public ResponseEntity<GenericResponse> deleteProduct(@PathVariable Long id) {
        try {
            if (service.get(id) != null) {
                service.deleteProductById(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Product deleted successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse("Product not found"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse(e.getMessage()));
        }
    }
}
