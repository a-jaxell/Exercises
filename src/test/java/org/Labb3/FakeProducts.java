package org.Labb3;

import org.Labb3.entities.Product;
import org.Labb3.entities.ProductCategory;

import java.util.ArrayList;

public class FakeProducts {

    static Product product1 = new Product("Microplane", ProductCategory.UTENSILS, 4);
    static Product product2 = new Product("Global_20_cm", ProductCategory.CHEF_KNIVES, 2);
    static Product product3 = new Product("Slickepott_L", ProductCategory.SPATULAS, 4);
    static Product product4 = new Product("Slickepott_S", ProductCategory.SPATULAS, 6);
    static Product product5 = new Product("ClickClack", ProductCategory.TONGS, 9);
    static Product product6 = new Product("Wusthof_27_cm", ProductCategory.CHEF_KNIVES, 8);
    // 7 & 8 are identical to serve as check for duplicates.
    static Product product7 = new Product("Sieve", ProductCategory.UTENSILS, 3);
    static Product product8 = new Product("Sieve", ProductCategory.UTENSILS, 3);
    static Product product9 = new Product("FingerSnipper4K", ProductCategory.MANDOLINS, 8);

     public static ArrayList<Product> fakeProducts = new ArrayList<>();

     public static ArrayList<Product> list(){
         fakeProducts.add(product1);
         fakeProducts.add(product2);
         fakeProducts.add(product3);
         fakeProducts.add(product4);
         fakeProducts.add(product5);
         fakeProducts.add(product6);
         fakeProducts.add(product7);
         fakeProducts.add(product8);
         fakeProducts.add(product9);

         System.out.println(fakeProducts);
         return fakeProducts;
     }

}
