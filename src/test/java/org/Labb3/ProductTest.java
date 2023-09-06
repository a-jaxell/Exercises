package org.Labb3;

import org.Labb3.entities.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.Labb3.entities.ProductCategory.CHEF_KNIVES;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    static Product product;
    @BeforeAll
    static void setUp(){
        product = new Product("Santoku 17cm",CHEF_KNIVES,5);
    }
    @Test
    @DisplayName("Getter for name should work")
    void testNameGetter(){
            assertEquals("Santoku 17cm", product.getName());
    }
}
