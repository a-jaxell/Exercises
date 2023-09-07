package org.Labb3.service;

import org.Labb3.entities.Product;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    @Test
    void testAddNewProduct(){
        ArrayList<Product> storage = new ArrayList<>();
        assertThat(storage).isEmpty();
        Warehouse.addNewProduct();
    }
}