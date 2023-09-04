package org.labb2;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class labb2 {
    public static void main(String[] args) {

        Rectangle one = new Rectangle();
        Circle two = new Circle(1.5);
        Rectangle three = new Rectangle(3.1, 7.9);
        Rectangle four = new Rectangle(1.1, 1.0);
        Circle five = new Circle(5.0);


        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(four);
        shapes.add(five);
        shapes.add(three);
        shapes.add(one);
        shapes.add(two);

        for(var shape : shapes){
            System.out.println(shape.getArea());
        }

        System.out.println();
        shapes.sort(null);

        for(var shape : shapes){
            System.out.println(shape.getArea());
        }
        
        Shape test = Shape.newShape("Circle", 1.0);
        System.out.println(test.getArea());
    }
}