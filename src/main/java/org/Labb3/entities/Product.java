package org.Labb3.entities;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Product {
    private final UUID id;
    private int rating;
    private String name;
    private ProductCategory category;
    private final LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;


    public Product(String name, ProductCategory category, int rating) {
        LocalDateTime date = LocalDateTime.now();
        this.id = UUID.randomUUID();
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = date;
        this.dateLastModified = date;
    }
    public Product(String name, ProductCategory category, int rating, LocalDateTime dateCreated) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateCreated;
    }

    public String getName() {
        return name;
    }
    public int getRating() {
        return rating;
    }

    public UUID getId() {
        return id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory newCategory) {
        this.category = newCategory;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setModifiedDate() {
        this.dateLastModified = LocalDateTime.now();
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }
}
