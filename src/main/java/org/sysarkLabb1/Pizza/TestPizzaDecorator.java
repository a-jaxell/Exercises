package org.sysarkLabb1.Pizza;

public class TestPizzaDecorator {
    public static void main(String[] args) {
        Pizza pizza = new SimplyNonVegPizza();

        pizza = new Broccoli(new Cheese(new Cheese(pizza)));

        System.out.println("Desc: " + pizza.getDesc());
        System.out.println("Price: " + pizza.getPrice());
    }
}
