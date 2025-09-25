package org.example.springsecuritycrud.infrastruture.dto;

public class CategoryDTO {
    private Long id;

    public CategoryDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
