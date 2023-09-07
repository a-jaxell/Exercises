package org.Labb3.service;

import org.Labb3.entities.Product;
import java.util.ArrayList;

public class Warehouse {

    public ArrayList<Product> getStorage() {
        return storage;
    }

    private static ArrayList<Product> storage = new ArrayList<>();
    public Warehouse() {
    }

    public static void addNewProduct(Product product) {
        if(product == null){
            throw new  IllegalArgumentException("Input cannot be null");
        }
        storage.add(product);
    }
}
