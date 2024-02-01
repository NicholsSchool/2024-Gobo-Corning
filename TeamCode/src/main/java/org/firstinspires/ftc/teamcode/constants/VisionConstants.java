package org.firstinspires.ftc.teamcode.constants;

/**
 * Constants for AprilTagVision and TensorFlowVision.
 * April Tag Field Coordinates are not stored here,
 * but can be found in <code>AprilTagGameDatabase.java</code>
 */
public interface VisionConstants {
    /** Camera Forward Offset from the robot center */
    double FORWARD_OFFSET = 5.5;

    /** Camera Horizontal Offset from the robot center */
    double HORIZONTAL_OFFSET = 0.0;

    /** Area of Large April Tags in inches squared */
    double BIG_TAG_AREA = 25.0;

    /** Area of Small April Tags in inches squared */
    double SMALL_TAG_AREA = 4.0;

    /** The Left Position Threshold for the Detection Center */
    double LEFT_PROP_THRESHOLD = 250.0;

    /** The Right Position Threshold for the Detection Center */
    double RIGHT_PROP_THRESHOLD = 550.0;
}