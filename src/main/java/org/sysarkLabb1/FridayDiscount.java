package org.sysarkLabb1;

import org.sysarkLabb1.entities.Product;

import java.time.LocalDateTime;

public class FridayDiscount extends BaseDiscount {
    private final Discount nextDiscount;

    public FridayDiscount(Discount discount) {
        this.nextDiscount = discount;
    }

    @Override
    public String getDescription(Product product) {
        String desc = isApplicable(product) ? "10 % on Fridays!" : "";
        return nextDiscount.getDescription(product) + " " + desc;
    }

    @Override
    protected boolean isApplicable(Product product) {
        return LocalDateTime.now().getDayOfWeek().toString().equals("FRIDAY");
    }

    @Override
    public double calculateDiscount(Product product) {
        return isApplicable(product) ? nextDiscount.apply(product) + (product.price() * product.quantity() * 0.10) : nextDiscount.apply(product) + 0;
    }
}
