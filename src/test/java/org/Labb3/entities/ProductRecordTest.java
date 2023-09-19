package org.Labb3.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRecordTest {

    Product product = new Product("Microplane", ProductCategory.UTENSILS, 5);

    @Test
    void shouldReturnNewRecordOfObject(){

        ProductRecord actual = ProductRecord.returnProduct(product);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(product.getId());
        assertThat(actual).isNotEqualTo(product);
    }
}