package org.firstinspires.ftc.teamcode.constants;

/**
 * Constants for the Drivetrain
 */
public interface DrivetrainConstants {
    /** The Maximum Speed of the Driving Profile */
    double DRIVE_PROFILE_SPEED = 3.0;

    /** The Maximum Speed of the Turning Profile */
    double TURN_PROFILE_SPEED = 1.0;

    /** Left Drive Wheel Angle Offset (30 degrees) */
    double LEFT_DRIVE_OFFSET = Math.PI / 6.0;

    /** Right Drive Wheel Angle Offset (150 degrees) */
    double RIGHT_DRIVE_OFFSET = 5.0 * Math.PI / 6.0;

    /** Back Drive Wheel Angle Offset (270 degrees) */
    double BACK_DRIVE_OFFSET = 1.5 * Math.PI;
}
