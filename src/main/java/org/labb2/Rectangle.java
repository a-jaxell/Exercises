package org.labb2;

public class Rectangle extends Shape{
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private double length;
    private double width;

    public Rectangle(double width, double length){
        this.length = length;
        this.width = width;
    }
    public Rectangle(){
        this(3.0, 6.0);
    }

    public double getArea() {
        return this.length * this.width;
    }

    public double getCircumference() {
        return this.width * 2 + this.length * 2;
    }
}
