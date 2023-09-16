package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Product> sortByName() {
        return storage.stream().sorted(Comparator.comparing(Product::getName)).toList();
    }

    public List<Product> getProductsCreatedAfter(LocalDateTime date) {
        return storage.stream()
                .filter(product -> product.getDateCreated().isAfter(date))
                .toList();
    }

    public List<Product> getProductsModified() {
        return storage.stream()
                .filter(Product::isModified)
                .toList();
    }
    public List<ProductCategory> getPopulatedCategories() {
        // Map each category with products
        Map<ProductCategory, List<Product>> categoriesWithProducts = storage.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        //Filter out all categories that are empty(have no products)
        return categoriesWithProducts.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .toList();
    }
}
