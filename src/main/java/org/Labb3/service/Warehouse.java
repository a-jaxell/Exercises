package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Warehouse {

    private ArrayList<Product> storage = new ArrayList<>();

    public Warehouse() {
    }
    public Warehouse(ArrayList<Product> storage) {
        this.storage = storage;
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
    public List<Product> sortByName(){
        return storage.stream().sorted(Comparator.comparing(Product::getName)).toList();
    }

    public List<Product> getProductsCreatedAfter(LocalDateTime date) {
        return storage.stream()
                .filter(product -> product.getDateCreated().isAfter(date))
                .toList();
    }
}
