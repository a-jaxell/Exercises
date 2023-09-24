package org.sysarkLabb1.GeneralDiscount;

import org.sysarkLabb1.entities.Product;

@FunctionalInterface
public interface CalculateDiscount {
    double calculateDiscount(Product product);
}
