package org.sysarkLabb1.Discounts;

import org.sysarkLabb1.entities.Product;

public interface Discount {
    double apply(Product product);

    String getDescription(Product product);
}
