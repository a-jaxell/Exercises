package org.Labb3.service;

import org.Labb3.FakeProducts;
import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private Product product;
    private LocalDateTime fixedDate;
    private LocalDateTime fixedDate2;
    private LocalDateTime fixedDate3;

    @BeforeEach
            void setUp(){
            Clock clock = Clock.fixed(Instant.ofEpochSecond(1631510400, 1000L), ZoneId.of("UTC"));
            Clock clock2 = Clock.fixed(Instant.ofEpochSecond(1633504400, 1000L), ZoneId.of("UTC"));
            Clock clock3 = Clock.fixed(Instant.ofEpochSecond(1637524400, 1000L), ZoneId.of("UTC"));

            fixedDate = LocalDateTime.now(clock);
            fixedDate2 = LocalDateTime.now(clock2);
            fixedDate3 = LocalDateTime.now(clock3);

            warehouse = new Warehouse();
            product = new Product("Microplane", ProductCategory.UTENSILS, 5);
    }
    private Product createProduct(String name, ProductCategory category, int rating){
        return new Product(name, category, rating);
    }
    private Product createProduct(String name, ProductCategory category, int rating, LocalDateTime date){
        return new Product(name, category, rating, date);
    }

    @Test
    void shouldAddNewProductToWarehouse() {
        assertThat(warehouse.getProducts()).isNullOrEmpty();
        warehouse.addNewProduct(product);
        assertThat(warehouse.getProducts()).isNotEmpty();
    }

    @Test
    void shouldGetAllProducts() {
        warehouse.addNewProduct(product);
        List<Product> productList = warehouse.getProducts();
        assertEquals(productList.size(), 1, "Warehouse should contain one product");
        assertEquals(productList.get(0), product, "Warehouse should contain the added product");
    }

    @Test
    void testGettingProductWithId_ShouldReturnProduct() {
        warehouse.addNewProduct(product);
        UUID id = product.getId();
        Product actual = warehouse.getProduct(id);
        assertEquals(product, actual);
    }

    @Test
    void testGettingProductWithWrongId_ShouldThrowException() {
        UUID id = product.getId();
        UUID wrongId = UUID.randomUUID();
        assertEquals(id.getClass(), wrongId.getClass());
        assertNotSame(id, wrongId);
        assertThrows(NullPointerException.class, () -> warehouse.getProduct(wrongId));
    }

    @Test
    void testModifyingProduct_ShouldSucceed() throws InterruptedException {
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
    void testSortingProductsAlphabetically_ShouldSucceed() {

        Warehouse warehouse = new Warehouse(FakeProducts.list());
        List<Product> expected = warehouse.sortByName();

        List<Product> actual = FakeProducts.list().stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();
        List<Product> notActual = FakeProducts.list();

        assertEquals(expected, actual, "Products should sort alphabetically(a-z)");
        assertNotEquals(expected, notActual);
    }

    @Test
    void shouldReturnAllProductsCreatedAfterACertainDate() {
        Warehouse warehouse1 = new Warehouse();
        warehouse1.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        warehouse1.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        warehouse1.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        List<Product> actual = warehouse1.getProductsCreatedAfter(fixedDate2);

        assertThat(actual).allMatch(product1 -> product1.getDateCreated().isAfter(fixedDate2));
    }

    @Test
    void shouldReturnAllModifiedProducts() {
        Warehouse warehouse1 = new Warehouse();

        warehouse1.addNewProduct(new Product("Sauce_Stirrer", ProductCategory.WHISKS, 1, fixedDate3));
        warehouse1.addNewProduct(new Product("Cheese_Grater", ProductCategory.UTENSILS, 5, fixedDate2));
        warehouse1.addNewProduct(new Product("Corn_Grabber", ProductCategory.TONGS, 2, fixedDate));

        UUID productOneId = warehouse1.getProducts().get(0).getId();

        warehouse1.modifyProduct(productOneId, "Sauce_swirler", ProductCategory.WHISKS, 8);
        List<Product> actual = warehouse1.getProductsModified();

        assertThat(warehouse1.getProducts()).contains(warehouse1.getProduct(productOneId));
        assertThat(actual).allMatch(Product::isModified);
    }
    @Test
    void shouldReturnAllCategoriesWithMinimumOneProduct(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());

        List<ProductCategory> expected = warehouse1.getPopulatedCategories();
        assertThat(ProductCategory.WHISKS).isNotIn(expected);
    }
    @Test
    void shouldReturnAmountOfProductsForGivenCategory(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());

        Map<ProductCategory, Long> expected = warehouse1.getProductsPerCategory();
        assertThat(expected).doesNotContainEntry(ProductCategory.WHISKS, 0L);
        assertThat(expected).containsEntry(ProductCategory.UTENSILS, 3L);

    }
    @Test
    void shouldReturnAmountOfProducts_WhenGivenACategory(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());

        String category = "WHISKS";
        String category2 = "uTenSILS";
        String category3 = "tongs";
        Map<ProductCategory, Long> expected = warehouse1.getProductsPerCategory(category);
        Map<ProductCategory, Long> expected2 = warehouse1.getProductsPerCategory(category2);
        Map<ProductCategory, Long> expected3 = warehouse1.getProductsPerCategory(category3);

        assertThat(expected).doesNotContainEntry(ProductCategory.WHISKS, 0L);
        assertThat(expected2).containsEntry(ProductCategory.UTENSILS, 3L);
        assertThat(expected3).containsEntry(ProductCategory.TONGS, 1L);
    }
}