package org.Labb3;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;

import java.util.ArrayList;
import java.util.Arrays;

public class FakeProducts {

    public static ArrayList<Product> fakeProducts = new ArrayList<>(Arrays.asList(
    new Product("Microplane",ProductCategory.UTENSILS, 4),
    new Product("Global_20_cm",ProductCategory.CHEF_KNIVES, 2),
    new Product("Slickepott_L",ProductCategory.SPATULAS, 4),
    new Product("Slickepott_S",ProductCategory.SPATULAS, 6),
    new Product("ClickClack",ProductCategory.TONGS, 9),
    new Product("Wusthof_27_cm",ProductCategory.CHEF_KNIVES, 8),
        // 7 & 8 are identical to serve as check for duplicates.
    new Product("Sieve",ProductCategory.UTENSILS, 3),
    new Product("Sieve",ProductCategory.UTENSILS, 3),
    new Product("FingerSnipper4K",ProductCategory.MANDOLINS, 8)
    ));

     public static ArrayList<Product> list(){
         return fakeProducts;
     }

}
