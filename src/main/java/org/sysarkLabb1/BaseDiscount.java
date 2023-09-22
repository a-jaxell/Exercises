package org.sysarkLabb1;

import org.sysarkLabb1.entities.Product;

public abstract class BaseDiscount implements Discount {
    // This is the decorator
    protected Discount nextDiscount;

    public BaseDiscount(Discount nextDiscount) {
        this.nextDiscount = nextDiscount;
    }

    public BaseDiscount() {
        this.nextDiscount = null;
    }

    protected abstract boolean isApplicable(Product product);

    protected abstract double calculateDiscount(Product product);

    @Override
    public double apply(Product product) {
        return calculateDiscount(product);
    }

    @Override
    public String getDescription(Product product) {
        return "Discount: " + nextDiscount.getDescription(product);
    }
}
