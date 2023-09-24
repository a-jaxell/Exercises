package org.sysarkLabb1.GeneralDiscount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sysarkLabb1.Discounts.Discount;
import org.sysarkLabb1.entities.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeneralDiscountTest {
    @BeforeEach
    void setUp(){

        CalculateDiscount calculateDiscount = product -> product.price() * 0.8;

    }

    @Test
    void whenGeneralDiscountAppliesDiscount_shouldReturnSumOfAllInChain(){

        Discount nextDiscount = mock(Discount.class);
        Product product = mock(Product.class);

        when(nextDiscount.apply(product)).thenReturn(10.0);
        GeneralDiscount generalDiscount = new GeneralDiscount(nextDiscount, product1 -> true, product1 -> 5.0, "Description");
        double actual = generalDiscount.apply(product);

        assertEquals(15.0, actual);

    }
    @Test
    void whenGeneralDiscountGetsDescription_shouldConcatAllInChain(){
        Discount nextDiscount = mock(Discount.class);
        Product product = mock(Product.class);

        when(nextDiscount.getDescription(product)).thenReturn("Previous description");
        GeneralDiscount generalDiscount = new GeneralDiscount(nextDiscount, product1 -> true, product1 -> 0, "Description");

        String actual = generalDiscount.getDescription(product);
        String expected = "Previous description Description";
        assertEquals(expected, actual);
    }
    @Test
    void whenLambdaIsAssignedToIsApplicable_itShouldReturnBoolean(){

        Product product = mock(Product.class);
        IsApplicable lambdaTrue = (p)-> product.name().equals("True");
        IsApplicable lambdaFalse = (p)-> product.name().equals("False");

        when(product.name()).thenReturn("True");

        assertTrue(lambdaTrue.isApplicable(product));
        assertFalse(lambdaFalse.isApplicable(product));

    }
    @Test
    void whenLambdaIsAssignedToCalculateDiscount_itShouldReturnDouble(){
        Product product = mock(Product.class);

        CalculateDiscount lambdaNoDiscount = (p) -> product.price();
        CalculateDiscount lambdaTwentyPercent = (p) -> product.price() * 0.8;

        when(product.price()).thenReturn(10.0);

        assertEquals(10.0, lambdaNoDiscount.calculateDiscount(product));
        assertEquals(8.0, lambdaTwentyPercent.calculateDiscount(product));
    }

}