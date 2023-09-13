package org.Labb3.service;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {


        Clock clock = Clock.fixed(Instant.ofEpochSecond(1631510400,1000L), ZoneId.of("UTC"));
        LocalDateTime fixedDate = LocalDateTime.now(clock);
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
    @Test
    void testModifyingProductSucceeds() throws InterruptedException {
        Warehouse warehouse = new Warehouse();
        //Calling alternate constructor for ability to set a fixed date.
        Product product = new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate);
        warehouse.addNewProduct(product);


        String newName = "Cheese_Slicer";
        int newRating = 1;
        ProductCategory newCategory = ProductCategory.CHEF_KNIVES;
        UUID id = product.getId();
        LocalDateTime lastModified = product.getDateLastModified();
        warehouse.modifyProduct(id, newName, newCategory, newRating);

        assertEquals(newRating, product.getRating(),"Should recieve the new rating");
        assertEquals(newName, product.getName(),"Should recieve the new name");
        assertEquals(newCategory, product.getCategory(),"Should recieve the new category");
        assertEquals(id, product.getId(), "Product Id shouldn't change");
        assertNotEquals(product.getDateLastModified(), lastModified,"Should update last modified date");
    }
}