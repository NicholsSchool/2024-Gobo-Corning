package org.firstinspires.ftc.teamcode.math;

/**
 * A Point (x, y)
 */
public class Point {
    /** X value */
    public double x;

    /** Y value */
    public double y;

    /**
     * Instantiates the Point
     *
     * @param x the x value
     * @param y the y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The distance between two Points
     *
     * @param otherPoint the Point to compare to
     *
     * @return the distance
     */
    public double distance(Point otherPoint) {
        return Math.hypot(x - otherPoint.x, y - otherPoint.y);
    }

    /**
     * The absolute value of the Point
     *
     * @return the distance from (0, 0)
     */
    public double magnitude() {
        return Math.hypot(x, y);
    }

    /**
     * The angle of the Point
     *
     * @return the angle from (0, 0) in radians
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Restricts the magnitude of the Point to the maximum
     * if it is above the maximum
     *
     * @param maxMagnitude the maximum Point magnitude
     *
     * @return this Point after the value restriction
     */
    public Point restrictMagnitude(double maxMagnitude) {
        double magnitude = magnitude();
        if(magnitude > maxMagnitude) {
            double ratio = maxMagnitude / magnitude;
            x *= ratio;
            y *= ratio;
        }
        return this;
    }

    /**
     * Scales this Point's magnitude, unless this Point's
     * magnitude is 0
     *
     * @param newMagnitude the new magnitude
     */
    public void scaleMagnitude(double newMagnitude) {
        double magnitude = magnitude();
        if(magnitude != 0) {
            double ratio = newMagnitude / magnitude;
            x *= ratio;
            y *= ratio;
        }
    }
}