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
     * Restricts the Point's magnitude
     *
     * @param maxMagnitude the maximum Point magnitude
     *
     * @return the Point with the value restriction,
     *         without altering the implicit values
     */
    public Point restrictMagnitude(double maxMagnitude) {
        double magnitude = Math.hypot(x, y);
        if(magnitude > maxMagnitude) {
            double ratio = maxMagnitude / magnitude;
            return new Point(x * ratio, y * ratio);
        }
        return this;
    }
}