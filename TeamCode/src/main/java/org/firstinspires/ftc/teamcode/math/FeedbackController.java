package org.firstinspires.ftc.teamcode.math;

import com.qualcomm.robotcore.util.Range;

/**
 * A Feedback Controller
 */
public class FeedbackController {
    private final double proportional;
    private final double minimum;
    private final double maximum;
    private final double verticalProportional;
    private final double verticalPosition;
    private double targetPosition;

    /**
     * Instantiates the FeedbackController
     *
     * @param p the proportional constant
     * @param target the initial target position
     * @param min the minimum output value
     * @param max the maximum output value
     * @param verticalP the proportional based on vertical error
     * @param verticalPos the vertical position
     */
    public FeedbackController(double p, double target, double min, double max, double verticalP, double verticalPos) {
        proportional = p;
        targetPosition = target;
        minimum = min;
        maximum = max;
        verticalProportional = verticalP;
        verticalPosition = verticalPos;
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
        return Range.clip(proportional * (targetPosition - position) +
                verticalProportional * Math.cos(Math.PI * position / verticalPosition), minimum, maximum);
    }
}