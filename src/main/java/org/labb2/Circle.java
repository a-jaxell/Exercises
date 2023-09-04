package org.labb2;

public class Circle extends Shape {
    private double radius;
    public double getRadius() {
        return radius;
    }
    public double getDiameter() {
        return radius * 2;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    public void setDiameter(double diameter) {
        this.radius = diameter / 2;
    }

    public Circle(){
        this(6.0);
    }
    public Circle(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return Math.pow(this.radius,2) * Math.PI;
    }

    public double getCircumference() {
        return radius * 2 * Math.PI;
    }
}
