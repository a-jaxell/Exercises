package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

        Warehouse warehouse = new Warehouse();
        Product product = new Product("Microplane", ProductCategory.UTENSILS, 5);
    @Test
    void testAddNewProduct(){
        assertThat(warehouse.getProducts()).isNullOrEmpty();
        warehouse.addNewProduct(product);
        assertThat(warehouse.getProducts()).isNotEmpty();
    }
    @Test
   void testGetAllProducts(){
        List<Product> productList = warehouse.getProducts();
        warehouse.addNewProduct(product);
        assertThat(productList).isExactlyInstanceOf(warehouse.getProducts().getClass());
    }
}