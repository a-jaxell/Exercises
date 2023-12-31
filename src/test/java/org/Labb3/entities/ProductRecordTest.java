package org.Labb3.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductRecordTest {

    private Product productForRecord;

    @BeforeEach
    void setUp() {
        productForRecord = new Product("Microplane", ProductCategory.UTENSILS, 5);
    }

    @Test
    void shouldReturnNewRecordOfObject() {

        ProductRecord actual = ProductRecord.returnRecord(productForRecord);

        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(productForRecord.getId());
        assertThat(actual).isNotEqualTo(productForRecord);
    }
}
