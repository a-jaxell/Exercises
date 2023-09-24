package org.sysarkLabb1.GeneralDiscount;

import org.sysarkLabb1.Discounts.Discount;
import org.sysarkLabb1.entities.Product;

public class GeneralDiscount implements Discount {

    private final Discount nextDiscount;
    private final IsApplicable isApplicable;
    private final CalculateDiscount calculateDiscount;
    private final String description;

    public GeneralDiscount(Discount discount, IsApplicable isApplicable, CalculateDiscount calculateDiscount, String description) {
        this.nextDiscount = discount;
        this.calculateDiscount = calculateDiscount;
        this.isApplicable = isApplicable;
        this.description = description;
    }

    public double apply(Product product) {
        return calculateDiscount(product);
    }

    public String getDescription(Product product) {
        String desc = (isApplicable.isApplicable(product) ? description : "");
        return nextDiscount.getDescription(product) + " " + desc;
    }

    public double calculateDiscount(Product product) {
        return isApplicable.isApplicable(product) ? (nextDiscount.apply(product) + calculateDiscount.calculateDiscount(product)) : (nextDiscount.apply(product) + 0);
    }

}