package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;

import java.util.ArrayList;
import java.util.UUID;

public class Warehouse {

    private final ArrayList<Product> storage = new ArrayList<>();

    public Warehouse() {
    }

    public ArrayList<Product> getProducts() {
        return this.storage;
    }

    public void addNewProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        storage.add(product);
    }

    public Product getProduct(UUID id) throws IllegalArgumentException, NullPointerException {
        Product result = storage.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
        if (result == null) {
            throw new NullPointerException("Error: Product Id does not exist");
        }
        return result;
    }

    public void modifyProduct(UUID id, String newName, ProductCategory newCategory, int newRating) {
        getProduct(id).update(newName, newCategory, newRating);

    }
}
