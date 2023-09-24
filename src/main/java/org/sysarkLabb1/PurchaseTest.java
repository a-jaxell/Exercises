package org.sysarkLabb1;

import org.sysarkLabb1.Discounts.*;
import org.sysarkLabb1.GeneralDiscount.GeneralDiscount;
import org.sysarkLabb1.entities.Product;

public class PurchaseTest {
    public static void main(String[] args) {
        Product wholeMilk = new Product("Whole Milk", 17.90, 2);
        Product concreteBlock = new Product("Concrete Block", 35.90, 20);
        Product decorateMagazine = new Product("Decor Monthly", 52.75, 5);
        Product comboDiscount = new Product("Milky milk milk", 7.0, 10);
        Product generalDiscount = new Product("Finest dirt", 1000, 1);

        Discount discount = new QuantityDiscount(new FridayDiscount(new MilkDiscount(new EmptyDiscount())));
        Discount discount1 = new FridayDiscount(new GeneralDiscount(new EmptyDiscount(), (product) -> product.price() > 100, product -> product.price()* 0.80,"20% off on items above 100kr"));

        System.out.println(discount1.apply(generalDiscount));
        System.out.println(discount1.getDescription(generalDiscount));


        System.out.println(discount.apply(wholeMilk));
        System.out.println(discount.getDescription(wholeMilk));

        System.out.println(discount.apply(concreteBlock));
        System.out.println(discount.getDescription(concreteBlock));

        System.out.println(discount.apply(decorateMagazine));
        System.out.println(discount.getDescription(decorateMagazine));

        System.out.println(discount.apply(comboDiscount));
        System.out.println(discount.getDescription(comboDiscount));

    }
}
