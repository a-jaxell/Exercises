package org.Labb3.service;

import org.Labb3.entities.Product;
import java.util.ArrayList;

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
}
