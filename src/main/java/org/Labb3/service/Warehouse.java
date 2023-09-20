package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.Labb3.entities.ProductRecord;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
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

    public void addNewProduct(String name, ProductCategory category, int rating) {
        storage.add(new Product(name, category, rating));
    }
    public void addNewProduct(Product product){
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

    public List<ProductRecord> getProductsCreatedAfter(LocalDateTime date) {
        return getProducts().stream()
                .filter(productRecord -> productRecord.dateCreated().isAfter(date))
                .toList();
    }

    public List<ProductRecord> getProductsModified() {
        Predicate<ProductRecord> isModified = productRecord -> !productRecord.dateCreated().isEqual(productRecord.dateModified());
        return getProducts().stream()
                .filter(isModified)
                .toList();
    }
    public List<ProductCategory> getPopulatedCategories() {
        // Map each category with products
        Map<ProductCategory, List<ProductRecord>> categoriesWithProducts = getProducts().stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
        //Filter out all categories that are empty(have no products)
        return categoriesWithProducts.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .toList();
    }
    public Map<ProductCategory, Long> getProductsPerCategory(){
        return getProducts().stream()
                .collect(Collectors.groupingBy(ProductRecord::category, Collectors.counting()));
    }
    public Map<ProductCategory, Long> getProductsPerCategory(String category){
        return getProducts().stream()
                .filter(productRecord -> productRecord.category().toString().equalsIgnoreCase(category))
                .collect(Collectors.groupingBy(ProductRecord::category, Collectors.counting()));
    }

    public Map<String, Long> numberPerFirstLetter() {
        return getProducts().stream()
                .map(ProductRecord::name)
                .map(s -> s.substring(0,1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<ProductRecord> getNewTrendingProducts() {
        return getProducts().stream()
                .filter(productRecord -> productRecord.rating() == 10)
                .filter(productRecord -> productRecord.dateCreated().getMonth() == LocalDateTime.now().getMonth() &&
                        productRecord.dateCreated().getYear() == LocalDateTime.now().getYear())
                .sorted(Comparator.comparing(ProductRecord::dateCreated))
                .collect(Collectors.toList());
    }
    public List<ProductRecord> getNewTrendingProducts(LocalDateTime date) {
        return getProducts().stream()
                .filter(productRecord -> productRecord.rating() == 10)
                .filter(productRecord -> productRecord.dateCreated().getMonth() == date.getMonth() &&
                        productRecord.dateCreated().getYear() == date.getYear())
                .sorted(Comparator.comparing(ProductRecord::dateCreated).reversed())
                .collect(Collectors.toList());
    }
}
