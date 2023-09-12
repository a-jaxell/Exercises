package org.Labb3.service;

import org.Labb3.entities.Product;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class Warehouse {

    private ArrayList<Product> storage = new ArrayList<>();
    public ArrayList<Product> getProducts() {
        return this.storage;
    }

    public Warehouse() {
    }

    public void addNewProduct(Product product) {
        if(product == null){
            throw new  IllegalArgumentException("Input cannot be null");
        }
        storage.add(product);
    }

    public Product getProduct(UUID id) throws IllegalArgumentException, NullPointerException{
        Product result = storage.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
        if(result == null){
            throw new NullPointerException("Error: Product Id does not exist");
        }
        return result;
    }
}
