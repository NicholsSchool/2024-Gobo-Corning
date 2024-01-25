package org.firstinspires.ftc.teamcode.math;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Restricts the rate of change of a value
 */
public class MotionProfile {
    private final ElapsedTime timer;
    private final double speed;
    private double value;

    /**
     * Instantiates the Motion Profile
     *
     * @param speed the maximum speed
     */
    public MotionProfile(double speed) {
        timer = new ElapsedTime();
        this.speed = speed;
    }

    /**
     * Calculates and stores the restricted output value
     *
     * @param inputValue the input value
     *
     * @return the restricted output value
     */
    public double calculate(double inputValue) {
        double timePassed = timer.time();
        timer.reset();

        double deltaValue = inputValue - value;
        double maxDelta = timePassed * speed;

        if(deltaValue > maxDelta)
            value += maxDelta;
        else if(deltaValue < -maxDelta)
            value -= maxDelta;
        else
            value = inputValue;

        return value;
    }
}