package org.firstinspires.ftc.teamcode.math;

import org.firstinspires.ftc.teamcode.constants.ParabolicSplineConstants;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Math for Parabolic Spline Paths
 */
public class ParabolicSpline implements ParabolicSplineConstants {
    private final Drivetrain drivetrain;
    private final boolean isBlueAlliance;

    /**
     * Instantiates the ParabolicSpline
     *
     * @param drivetrain the drivetrain
     * @param isBlueAlliance whether we are blue alliance
     */
    public ParabolicSpline(Drivetrain drivetrain, boolean isBlueAlliance) {
        this.drivetrain = drivetrain;
        this.isBlueAlliance = isBlueAlliance;
    }
}
