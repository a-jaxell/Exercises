package org.Labb3.service;

import org.Labb3.FakeProducts;
import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {


    Clock clock = Clock.fixed(Instant.ofEpochSecond(1631510400, 1000L), ZoneId.of("UTC"));
    Clock clock2 = Clock.fixed(Instant.ofEpochSecond(1633504400, 1000L), ZoneId.of("UTC"));
    Clock clock3 = Clock.fixed(Instant.ofEpochSecond(1637524400, 1000L), ZoneId.of("UTC"));
    LocalDateTime fixedDate = LocalDateTime.now(clock);
    LocalDateTime fixedDate2 = LocalDateTime.now(clock2);
    LocalDateTime fixedDate3 = LocalDateTime.now(clock3);
    Warehouse warehouse = new Warehouse();
    Product product = new Product("Microplane", ProductCategory.UTENSILS, 5);


    @Test
    void testAddNewProduct() {
        assertThat(warehouse.getProducts()).isNullOrEmpty();
        warehouse.addNewProduct(product);
        assertThat(warehouse.getProducts()).isNotEmpty();
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = warehouse.getProducts();
        warehouse.addNewProduct(product);
        assertThat(productList).isExactlyInstanceOf(warehouse.getProducts().getClass());
    }

    @Test
    void testGettingProductWithIdShouldReturnProduct() {
        warehouse.addNewProduct(product);
        UUID id = product.getId();
        Product actual = warehouse.getProduct(id);
        assertEquals(product, actual);
    }

    @Test
    void testGettingProductWithWrongIdShouldThrowException() {
        UUID id = product.getId();
        UUID wrongId = UUID.randomUUID();
        assertEquals(id.getClass(), wrongId.getClass());
        assertNotSame(id, wrongId);
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

        assertEquals(newRating, product.getRating(), "Should recieve the new rating");
        assertEquals(newName, product.getName(), "Should recieve the new name");
        assertEquals(newCategory, product.getCategory(), "Should recieve the new category");
        assertEquals(id, product.getId(), "Product Id shouldn't change");
        assertNotEquals(product.getDateLastModified(), lastModified, "Should update last modified date");
    }
    @Test
    void testSortingProductsByNamesAlphabetically(){

        Warehouse warehouse = new Warehouse(FakeProducts.list());
            List<Product> expected = warehouse.sortByName();

            List<Product> actual = FakeProducts.list().stream()
                    .sorted(Comparator.comparing(Product::getName))
                    .toList();
            List<Product> notActual = FakeProducts.list();

        assertEquals(expected,actual);
        assertNotEquals(expected, notActual);
    }
    @Test
    void testGettingAllProductsCreatedAfterACertainDate(){
        Warehouse warehouse1 = new Warehouse();
        warehouse1.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        warehouse1.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        warehouse1.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        List<Product> actual = warehouse1.getProductsCreatedAfter(fixedDate2);

        assertThat(actual).allMatch(product1 -> product1.getDateCreated().isAfter(fixedDate2));
    }
    @Test
    void testGettingAllModifiedAfterCreated(){
        Warehouse warehouse1 = new Warehouse();

        warehouse1.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        warehouse1.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        warehouse1.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        UUID productOneId = warehouse1.getProducts().get(0).getId();

        warehouse1.modifyProduct(productOneId,"Sauce_swirler",ProductCategory.WHISKS, 8);
        List<Product> actual = warehouse1.getProductsModified();

        assertThat(warehouse1.getProducts()).contains(warehouse1.getProduct(productOneId));
        assertThat(actual).allMatch(Product::isModified);
    }
}