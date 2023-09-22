package org.sysarkLabb1;

import org.sysarkLabb1.entities.Product;

public class MilkDiscount extends BaseDiscount{
    private final Discount nextDiscount;
    public MilkDiscount(Discount discount) {
        this.nextDiscount = discount;
    }

    @Override
    public String getDescription(Product product){
        String desc = isApplicable(product) ? "10 % on milk": "";
        return nextDiscount.getDescription(product) + desc;
    }
    @Override
    protected boolean isApplicable(Product product) {
        return product.name().toLowerCase().contains("milk");
    }

    @Override
    public double calculateDiscount(Product product) {
        double discount = isApplicable(product) ? product.price()* product.quantity()* 0.10 : 0;
        return discount;
    }
}
