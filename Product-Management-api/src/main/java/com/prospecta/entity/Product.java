package com.prospecta.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Product {
	 private Long id;

	    @NotBlank(message = "Title is mandatory")
	    private String title;

	    @NotBlank(message = "Description is mandatory")
	    private String description;

	    @NotBlank(message = "Category is mandatory")
	    private String category;

	    @NotNull(message = "Price is mandatory")
	    @Positive(message = "Price must be greater than zero")
	    private Double price;

	    private String image;


    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
