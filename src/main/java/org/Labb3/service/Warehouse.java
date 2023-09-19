package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.Labb3.entities.ProductRecord;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Warehouse {

    private ArrayList<Product> storage = new ArrayList<>();

    public Warehouse() {
    }

    public Warehouse(ArrayList<Product> storage) {
        this.storage = storage;
    }

    public List<ProductRecord> getProducts() {
        return storage.stream().map(ProductRecord::returnRecord).toList();
    }

    public void addNewProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        storage.add(product);
    }

    public ProductRecord getProduct(UUID id) throws IllegalArgumentException, NullPointerException {
        Product result = storage.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
        if (result == null) {
            throw new NullPointerException("Error: Product Id does not exist");
        }
        return ProductRecord.returnRecord(result);
    }

    public void modifyProduct(UUID id, String newName, ProductCategory newCategory, int newRating) {
        Optional<Product> productToModify = findProduct(id);

        if (productToModify.isPresent()) {
            productToModify.ifPresent(product -> {
                product.update(newName, newCategory, newRating);
            });
        }
    }
    private Optional<Product> findProduct(UUID id) {
        return storage.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public List<ProductRecord> sortByName() {
        return getProducts().stream().sorted(Comparator.comparing(ProductRecord::name)).toList();
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
    public Map<ProductCategory, Long> getProductsPerCategory(){
        return storage.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }
    public Map<ProductCategory, Long> getProductsPerCategory(String category){
        return storage.stream()
                .filter(product -> product.getCategory().toString().equalsIgnoreCase(category))
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    public Map<String, Long> numberPerFirstLetter() {
        return storage.stream()
                .map(Product::getName)
                .map(s -> s.substring(0,1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<Product> getNewTrendingProducts() {
        return storage.stream()
                .filter(product -> product.getRating() == 10)
                .filter(product -> product.getDateCreated().getMonth() == LocalDateTime.now().getMonth() &&
                        product.getDateCreated().getYear() == LocalDateTime.now().getYear())
                .sorted(Comparator.comparing(Product::getDateCreated))
                .collect(Collectors.toList());
    }
    public List<Product> getNewTrendingProducts(LocalDateTime date) {
        return storage.stream()
                .filter(product -> product.getRating() == 10)
                .filter(product -> product.getDateCreated().getMonth() == date.getMonth() &&
                        product.getDateCreated().getYear() == date.getYear())
                .sorted(Comparator.comparing(Product::getDateCreated).reversed())
                .collect(Collectors.toList());
    }
}
