package org.firstinspires.ftc.teamcode.constants;

/**
 * Constants for the Arm
 */
public interface ArmConstants {
    /** Plane Launcher Servo Minimum Position */
    double PLANE_MIN = 0.35;

    /** Plane Launcher Servo Maximum Position */
    double PLANE_MAX = 1.0;

    /** Maximum Shoulder Motor Power */
    double SHOULDER_MAX = 0.3;

    /** Maximum Climbing Motor Power */
    double CLIMB_MAX = 0.75;

    /** Shoulder Proportional Constant */
    double SHOULDER_P = 0.001;

    /** Shoulder Vertical Proportional Constant */
    double VERTICAL_P = 0.05;

    /** Arm Vertical Encoder Position */
    double ARM_VERTICAL = 2770.0;

    /** Maximum Wrist Motor Power */
    double WRIST_MAX = 0.2;

    /** Wrist Proportional Constant */
    double WRIST_P = 0.001;

    /** Wrist intaking virtual fourbar position */
    double WRIST_INTAKING = 110.0;

    /** Wrist scoring virtual fourbar position */
    double WRIST_SCORING = 1450.0;

    /** Arm position to switch the wrist virtual fourbar position at */
    double FOURBAR_SWITCHING_POSITION = 300;
}