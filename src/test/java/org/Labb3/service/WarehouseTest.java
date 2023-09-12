package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Test
    void testGettingProductWithIdShouldReturnProduct(){
        warehouse.addNewProduct(product);
        UUID id = product.getId();
        Product actual = warehouse.getProduct(id);
        assertEquals(product, actual);
    }
    @Test
    void testGettingProductWithWrongIdShouldThrowException(){
        UUID id = product.getId();
        UUID wrongId = UUID.randomUUID();
        assertEquals(id.getClass(), wrongId.getClass());
        assertFalse(id == wrongId);
        assertThrows(NullPointerException.class, () -> warehouse.getProduct(wrongId));
    }
}