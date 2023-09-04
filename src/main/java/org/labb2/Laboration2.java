package org.labb2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Laboration2 {
    public static void main(String[] args) {

        Rectangle one = new Rectangle();
        Circle two = new Circle(1.5);
        Rectangle three = new Rectangle(2, 10);
        Rectangle four = new Rectangle(10, 2);
        Circle five = new Circle(1.5);


        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(four);
        shapes.add(five);
        shapes.add(three);
        shapes.add(one);
        shapes.add(two);

        for (var shape : shapes) {
            System.out.println(shape.getArea());
        }

        System.out.println();
        shapes.sort(null);

        for (var shape : shapes) {
            System.out.println(shape.getArea());
        }
        System.out.println();
        Shape test = Shape.newShape("Circle", 1.0);
        System.out.println(test.getArea());
        System.out.println();
        Set<Shape> shapeSet = new HashSet<>();
        shapeSet.add(two);
        shapeSet.add(one);
        shapeSet.add(three);
        shapeSet.add(five);
        shapeSet.add(four);

        for (var shape : shapeSet) {
            System.out.println(shape.getArea());
        }

    }
}