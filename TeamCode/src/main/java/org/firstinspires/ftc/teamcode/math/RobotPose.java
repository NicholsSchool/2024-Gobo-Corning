package org.firstinspires.ftc.teamcode.math;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * The Robot Pose (x, y, theta)
 */
public class RobotPose {
    /** X value */
    public double x;

    /** Y value */
    public double y;

    /** Angle value in radians */
    public double angle;

    /**
     * Instantiates the RobotPose
     *
     * @param x the x value
     * @param y the y value
     * @param angle the angle value in radians
     */
    public RobotPose(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    /**
     * Returns the Pose data as a String
     *
     * @return (x, y) | angle
     */
    @NonNull
    public String toString() {
        return String.format(Locale.US, "(%.1f, %.1f) | %.1f", x, y, Angles.clipDegrees(Math.toDegrees(angle)));
    }
}