package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.math.CartesianMotionProfile;
import org.firstinspires.ftc.teamcode.math.MotionProfile;
import org.firstinspires.ftc.teamcode.math.Point;
import org.firstinspires.ftc.teamcode.math.RobotPose;
import org.firstinspires.ftc.teamcode.math.SimpleFeedbackController;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Robot Drivetrain
 */
public class Drivetrain implements DrivetrainConstants {
    private final boolean isBlueAlliance;
    private final DcMotorEx leftDrive, rightDrive, backDrive, frontOdometry, leftOdometry, rightOdometry;
    private final AHRS navx;
    private final CartesianMotionProfile driveProfile;
    private final MotionProfile turnProfile;
    private final SimpleFeedbackController turnController;
    private final RobotPose pose;
    private double previousHeading, imuOffset, desiredHeading;
    private int previousLeftPosition, previousRightPosition, previousFrontPosition;

    /**
     * Initializes the Drivetrain subsystem
     *
     * @param hwMap the hardwareMap
     * @param isBlueAlliance whether we are blue alliance
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     * @param initialHeading the initial robot heading in radians
     */
    public Drivetrain(HardwareMap hwMap, boolean isBlueAlliance, double x, double y, double initialHeading) {
        this.isBlueAlliance = isBlueAlliance;
        this.previousHeading = initialHeading;
        this.imuOffset = initialHeading;
        this.desiredHeading = initialHeading;
        pose = new RobotPose(x, y, initialHeading);

        leftDrive = hwMap.get(DcMotorEx.class, "leftDrive");
        rightDrive = hwMap.get(DcMotorEx.class, "rightDrive");
        backDrive = hwMap.get(DcMotorEx.class, "backDrive");
        leftOdometry = hwMap.get(DcMotorEx.class, "leftOdometry");
        rightOdometry = hwMap.get(DcMotorEx.class, "rightOdometry");
        frontOdometry = hwMap.get(DcMotorEx.class, "rightShoulder");

        leftDrive.setDirection(DcMotorEx.Direction.REVERSE);
        rightDrive.setDirection(DcMotorEx.Direction.REVERSE);
        backDrive.setDirection(DcMotorEx.Direction.REVERSE);
        leftOdometry.setDirection(DcMotorEx.Direction.FORWARD);
        rightOdometry.setDirection(DcMotorEx.Direction.REVERSE);
        frontOdometry.setDirection(DcMotorEx.Direction.REVERSE);

        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftOdometry.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightOdometry.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontOdometry.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backDrive.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftOdometry.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOdometry.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontOdometry.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backDrive.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftOdometry.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightOdometry.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontOdometry.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        navx = AHRS.getInstance(hwMap.get(NavxMicroNavigationSensor.class,
                "navx"), AHRS.DeviceDataType.kProcessedData);

        driveProfile = new CartesianMotionProfile(DRIVE_PROFILE_SPEED);
        turnProfile = new MotionProfile(TURN_PROFILE_SPEED);
        turnController = new SimpleFeedbackController()
    }

    /**
     * Drives the robot field oriented
     *
     * @param driveInput the (x, y) input
     * @param turnInput the turning input
     * @param autoAlign whether to autoAlign
     * @param lowGear whether to put the robot to virtual low gear
     */
    public void drive(Point driveInput, double turnInput, boolean autoAlign, boolean lowGear) {
        turn = turnProfile.calculate(autoAlign ? turnToAngle() : turnInput);

        driveInput = driveProfile.calculate(driveInput);
        double power = Math.hypot(driveInput.y, driveInput.x);
        double angle = Math.atan2(driveInput.y, driveInput.x);

        leftDrive.setPower(turn + power * Math.cos(Math.toRadians(angle + LEFT_DRIVE_OFFSET - pose.angle)));
        rightDrive.setPower(turn + power * Math.cos(Math.toRadians(angle + RIGHT_DRIVE_OFFSET - pose.angle)));
        backDrive.setPower(turn + power * Math.cos(Math.toRadians(angle + BACK_DRIVE_OFFSET - pose.angle)));
    }

    private double turnToAngle() {
        double error = AngleMath.addAnglesRadians(heading, -desiredHeading);
        return Math.abs(error) < AUTO_ALIGN_ERROR ? 0.0 : error * AUTO_ALIGN_P;
    }
}