package org.sysarkLabb1.Pizza;

public class Cheese extends PizzaDecorator{

    private final Pizza pizza;

    public Cheese(Pizza pizza) {
        this.pizza = pizza;
    }
    @Override
    public String getDesc(){
        return pizza.getDesc() + ", Cheese(4.25)";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 4.25;
    }
}
