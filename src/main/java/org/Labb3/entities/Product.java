package org.Labb3.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final LocalDateTime dateCreated;
    private int rating;
    private String name;
    private ProductCategory category;
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

    public void setName(String newName) {
        this.name = newName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getId() {
        return id;
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

    public void update(String newName, ProductCategory newCategory, int newRating) {
        name = newName;
        category = newCategory;
        rating = newRating;
        dateLastModified = LocalDateTime.now();
    }

    public boolean isModified() {
        return !dateCreated.isEqual(dateLastModified);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
