package org.sysarkLabb1;

import org.sysarkLabb1.entities.Product;

// Null object to be placed in the end of the chain of discount checks
public class EmptyDiscount extends BaseDiscount{

    public EmptyDiscount() {
    }
    @Override
    public String getDescription(Product product){
        return "";
    }
    @Override
    protected boolean isApplicable(Product product) {
        return false;
    }

    @Override
    public double calculateDiscount(Product product) {
        return 0;
    }
}
