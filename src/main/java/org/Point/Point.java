package org.Point;
/*
X Skapa en Point klass
X Har en position x och y.
X Skapa lämpliga konstruktorer och setter/getter methoder.
X Skapa en metod distanceTo(Point other)
X Implementera funktionen att beräkna avståndet mellan två punkter.
X Använd statiska metoden Math.sqrt()
X Implementera CopyConstructor för klassen Point.
*/

public class Point {
    private double positionY;
    private double positionX;
    public double getPositionY() {
        return positionY;
    }
    public Point(){
        this(100, 100);
    }
    public Point(double x, double y){
        this.positionX = x;
        this.positionY = y;
    }
    public Point(Point Other){
        this.positionX = Other.positionX;
        this.positionY = Other.positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double distanceTo(Point other){
        // Using the pythagorean theorem to calculate the hypotenuse
        // in a 2d plane.

        // double diffX = this.positionX - Other.positionX;
        // double diffY = this.positionX - Other.positionY;
        // double distanceSquared = (diffX * diffX) + (diffY * diffY);

        return Math.sqrt(Math.pow(positionX -other.positionX,2) + Math.pow(positionY- other.positionY, 2));
    }
    public static Point newPoint(Point point){
        return new Point(point);
    }
}
