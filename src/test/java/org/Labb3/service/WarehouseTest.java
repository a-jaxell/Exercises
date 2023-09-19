package org.Labb3.service;

import org.Labb3.FakeProducts;
import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;
import org.Labb3.entities.ProductRecord;
import org.assertj.core.api.Assertions;
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
import static org.assertj.core.api.MapAssert.assertThatMap;
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
        List<ProductRecord> productList = warehouse.getProducts();
        assertThat(productList).isInstanceOf(List.class);
        System.out.println(productList);
        assertEquals(productList.size(), 1, "Warehouse should contain one product");
        assertEquals(productList.get(0), product, "Warehouse should contain the added product");
    }

    @Test
    void testGettingProductWithId_ShouldReturnProduct() {
        warehouse.addNewProduct(product);
        UUID id = product.getId();
        ProductRecord actual = warehouse.getProduct(id);
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

        UUID productOneId = warehouse1.getProducts().get(0).id();

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
    @Test
    void shouldReturnMapWithFirstLetterAsKeyAndNumberOfProducts_whenGivenListOfProducts(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());

        Map actual = warehouse1.numberPerFirstLetter();

        assertThatMap(actual).isNotNull();
        assertThatMap(actual).isNotEmpty();
        assertThatMap(actual).containsEntry("S",4L);
    }
    @Test
    void shouldReturnTrendingNewProducts_whenGivenListOfProducts(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());

        Product hotProduct = new Product("New_Hot_product", ProductCategory.UTENSILS, 10);
        Product oldHotProduct = new Product("Old_Hot_product", ProductCategory.TONGS, 10, fixedDate);
        warehouse1.addNewProduct(hotProduct);
        warehouse1.addNewProduct(oldHotProduct);

        List<Product> actual = warehouse1.getNewTrendingProducts();

        assertThat(actual).isNotNull();
        assertThat(actual).contains(hotProduct);
        assertThat(actual).doesNotContain(oldHotProduct);
    }
    @Test
    void shouldSortDatesByMostRecent(){
        Warehouse warehouse1 = new Warehouse(FakeProducts.list());
        LocalDateTime today = fixedDate;

        Product newProduct = new Product("New_product", ProductCategory.UTENSILS, 10, today.minusDays(5));
        Product newProduct2 = new Product("New_product2", ProductCategory.TONGS, 10, today.minusDays(30));
        Product newProduct3 = new Product("New_product3", ProductCategory.WHISKS, 10, today.minusDays(1));
        Product newProduct4 = new Product("New_product4", ProductCategory.MANDOLINS, 9, today.minusDays(2));
        warehouse1.addNewProduct(newProduct);
        warehouse1.addNewProduct(newProduct2);
        warehouse1.addNewProduct(newProduct3);
        warehouse1.addNewProduct(newProduct4);

        List<Product> actual = warehouse1.getNewTrendingProducts(fixedDate);
        Comparator<Product> comparator = Comparator.comparing(Product::getDateCreated, Comparator.reverseOrder());

        assertThat(actual).isNotNull();
        assertThat(actual).contains(newProduct);
        assertThat(actual).doesNotContain(newProduct2);
        assertThat(actual).contains(newProduct3);
        assertThat(actual).doesNotContain(newProduct4);
        assertThat(actual).isSortedAccordingTo(comparator);
    }
}