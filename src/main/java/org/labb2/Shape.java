package org.labb2;

abstract class Shape implements Comparable<Shape> {
    abstract double getArea();

    abstract double getCircumference();

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.getArea(), other.getArea());
    }

    public static Shape newShape(String type, double... params) {
        return switch (type) {
            case "Circle" -> {
                if (params.length > 1) {
                    throw new IllegalArgumentException("Circle requires diameter only");
                }
                yield new Circle(params[0]);
            }
            case "Rectangle" -> {
                if (params.length > 2) {
                    throw new IllegalArgumentException("Circle requires width and length");
                }
                yield new Rectangle(params[0], params[1]);
            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
