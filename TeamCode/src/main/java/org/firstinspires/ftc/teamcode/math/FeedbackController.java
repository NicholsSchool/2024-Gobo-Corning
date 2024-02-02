package org.firstinspires.ftc.teamcode.math;

/**
 * A Feedback Controller
 */
public class FeedbackController {
    private final double proportional;
    private final double verticalProportional;
    private final double cosineCoefficient;
    private final double horizontalPosition;
    private double targetPosition;

    /**
     * Instantiates the FeedbackController
     *
     * @param p the proportional constant
     * @param target the initial target position
     * @param v the power constant based on vertical error
     * @param verticalPos the vertical position
     */
    public FeedbackController(double p, double target, double v, double verticalPos, double horizontalPos) {
        proportional = p;
        targetPosition = target;
        verticalProportional = v;
        cosineCoefficient = Math.PI / (2.0 * (verticalPos - horizontalPos));
        horizontalPosition = horizontalPos;
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
        return proportional * (targetPosition - position) +
                verticalProportional * Math.cos((position - horizontalPosition) * cosineCoefficient);
    }
}