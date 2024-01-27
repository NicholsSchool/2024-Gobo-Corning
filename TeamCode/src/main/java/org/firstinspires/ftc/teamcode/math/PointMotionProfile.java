package org.firstinspires.ftc.teamcode.math;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Restricts the rate of change of a Point on an (x, y) plane
 */
public class PointMotionProfile {
    private final ElapsedTime timer;
    private final Point point;
    private final double speed;

    /**
     * Instantiates the Motion Profile
     *
     * @param speed the maximum speed
     */
    public PointMotionProfile(double speed) {
        timer = new ElapsedTime();
        point = new Point(0.0, 0.0);
        this.speed = speed;
    }

    /**
     * Calculates and stores the restricted output value
     *
     * @param inputPoint the input Point
     *
     * @return the restricted output point
     */
    public Point calculate(Point inputPoint) {
        double timePassed = timer.time();
        timer.reset();

        double delta = point.distance(inputPoint);
        double maxDelta = speed * timePassed;

        if(delta <= maxDelta) {
            point.x = inputPoint.x;
            point.y = inputPoint.y;
        }
        else {
            double ratio = maxDelta / delta;
            point.x += (inputPoint.x - point.x) * ratio;
            point.y += (inputPoint.y - point.y) * ratio;
        }

        return point;
    }
}