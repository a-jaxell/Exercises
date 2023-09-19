package org.Labb3.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRecord(UUID id, String name, ProductCategory category, int rating, LocalDateTime dateCreated, LocalDateTime dateModified){
    public ProductRecord(UUID id, String name, ProductCategory category, int rating, LocalDateTime dateCreated, LocalDateTime dateModified) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public static ProductRecord returnRecord(Product product) {
        ProductRecord result = new ProductRecord(product.getId(), product.getName(), product.getCategory(), product.getRating(), product.getDateCreated(), product.getDateLastModified());
        return result;
    }
}
