package org.sysarkLabb1.Discounts;

import org.sysarkLabb1.entities.Product;

public class QuantityDiscount extends BaseDiscount {

    private final Discount nextDiscount;

    public QuantityDiscount(Discount discount) {
        this.nextDiscount = discount;
    }

    @Override
    public String getDescription(Product product) {
        String desc = isApplicable(product) ? "10kr per item discount on quantities over 5" : "";
        return nextDiscount.getDescription(product) + " " + desc;
    }

    @Override
    protected boolean isApplicable(Product product) {
        return product.quantity() > 5;
    }

    @Override
    protected double calculateDiscount(Product product) {
        return isApplicable(product) ? nextDiscount.apply(product) + (product.quantity() * 10) : nextDiscount.apply(product) + 0;
    }
}
