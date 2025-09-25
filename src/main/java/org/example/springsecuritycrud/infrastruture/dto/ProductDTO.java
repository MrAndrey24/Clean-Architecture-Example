package org.example.springsecuritycrud.infrastruture.dto;

public class ProductDTO {
    private Long id;

    public ProductDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
