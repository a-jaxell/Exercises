package org.sysarkLabb1.Pizza;

public abstract class PizzaDecorator implements Pizza {
    private Pizza pizza;
    @Override
    public String getDesc(){
        return "Toppings";
    }
}
