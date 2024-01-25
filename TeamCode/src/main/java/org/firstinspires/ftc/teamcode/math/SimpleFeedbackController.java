package org.firstinspires.ftc.teamcode.math;

import com.qualcomm.robotcore.util.Range;

/**
 * A Simple Feedback Controller
 */
public class SimpleFeedbackController {
    private final double proportional;
    private final double minimum;
    private final double maximum;
    private double targetPosition;

    /**
     * Instantiates the SimpleFeedbackController
     *
     * @param p the proportional constant
     * @param target the initial target position
     * @param min the minimum output value
     * @param max the maximum output value
     */
    public SimpleFeedbackController(double p, double target, double min, double max) {
        proportional = p;
        targetPosition = target;
        minimum = min;
        maximum = max;
    }

    /**
     * Sets the new Target Position
     *
     * @param newPosition the new position
     */
    public void setTargetPosition(double newPosition) {
        targetPosition = newPosition;
    }

    /**
     * Calculates the output value based on the error
     *
     * @param position the current position
     *
     * @return the output value
     */
    public double calculate(double position) {
        return Range.clip(proportional * (targetPosition - position), minimum, maximum);
    }
}