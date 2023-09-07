package org.Labb3.entities;
import java.util.Date;
import java.util.UUID;

public class Product {
    private final UUID id;
    private int rating;
    private String name;
    private ProductCategory category;
    private final Date dateCreated;
    private Date dateLastModified;


    public Product(String name, ProductCategory category, int rating) {
        Date date = new Date();
        this.id = UUID.randomUUID();
        this.name = name;
        this.rating = rating;
        this.category = category;
        this.dateCreated = date;
        this.dateLastModified = date;
    }

    public String getName() {
        return name;
    }
    public int getRating() {
        return rating;
    }
}
