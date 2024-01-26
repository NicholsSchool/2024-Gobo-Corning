package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.teamcode.math.Point;

/**
 * A Controller Joystick
 */
public class Joystick {
    /** X value */
    public Axis x;

    /** Y value */
    public Axis y;

    /**
     * Instantiates the Joystick
     */
    public Joystick() {
        x = new Axis();
        y = new Axis();
    }

    /**
     * The Joystick Position
     *
     * @return the position as a Point
     */
    public Point position() {
        return new Point(x.value(), y.value());
    }

    /**
     * Updates the Joystick Axes
     *
     * @param newX the new X value
     * @param newY the new Y value
     */
    public void update(double newX, double newY) {
        x.update(newX);
        y.update(newY);
    }
}
