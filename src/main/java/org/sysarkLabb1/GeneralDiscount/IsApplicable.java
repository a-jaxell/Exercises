package org.sysarkLabb1.GeneralDiscount;

import org.sysarkLabb1.entities.Product;

@FunctionalInterface
public interface IsApplicable {
    boolean isApplicable(Product product);
}
