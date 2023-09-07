package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

        Warehouse warehouse = new Warehouse();
        Product product = new Product("Microplane", ProductCategory.UTENSILS, 5);

    @Test
    void testAddNewProduct(){
        assertThat(warehouse.getStorage()).isNullOrEmpty();
        Warehouse.addNewProduct(product);
        assertThat(warehouse.getStorage()).isNotEmpty();
    }
}