package org.labb2;

import java.util.Objects;

public class Rectangle extends Shape {
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

    public Rectangle(double width, double length) {
        this.length = length;
        this.width = width;
    }

    public Rectangle() {
        this(3.0, 6.0);
    }

    public double getArea() {
        return this.length * this.width;
    }

    public double getCircumference() {
        return this.width * 2 + this.length * 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(length, rectangle.length) == 0 && Double.compare(getArea(), rectangle.getArea()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, getArea());
    }
}
