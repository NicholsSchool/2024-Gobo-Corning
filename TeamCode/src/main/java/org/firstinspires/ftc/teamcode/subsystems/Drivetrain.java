package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.math.Angles;
import org.firstinspires.ftc.teamcode.math.PointMotionProfile;
import org.firstinspires.ftc.teamcode.math.MotionProfile;
import org.firstinspires.ftc.teamcode.math.Point;
import org.firstinspires.ftc.teamcode.math.RobotPose;
import org.firstinspires.ftc.teamcode.math.SimpleFeedbackController;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Robot Drivetrain
 */
public class Drivetrain implements DrivetrainConstants {
    private final DcMotorEx leftDrive, rightDrive, backDrive, frontOdometry, leftOdometry, rightOdometry;
    private final AHRS navx;
    private final PointMotionProfile driveProfile;
    private final MotionProfile turnProfile;
    private final SimpleFeedbackController turnController;
    private final RobotPose pose;
    private double previousHeading, imuOffset, targetHeading;
    private int previousLeftPosition, previousRightPosition, previousFrontPosition;

    /**
     * Initializes the Drivetrain subsystem
     *
     * @param hwMap the hardwareMap
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     * @param initialHeading the initial robot heading in radians
     */
    public Drivetrain(HardwareMap hwMap, boolean isBlueAlliance, double x, double y, double initialHeading) {
        this.previousHeading = initialHeading;
        this.imuOffset = initialHeading;
        this.targetHeading = initialHeading;
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

        leftDrive.setVelocityPIDFCoefficients(0.0, 0.0, 0.0, LEFT_DRIVE_FF);
        rightDrive.setVelocityPIDFCoefficients(0.0, 0.0, 0.0, RIGHT_DRIVE_FF);
        backDrive.setVelocityPIDFCoefficients(0.0, 0.0, 0.0, BACK_DRIVE_FF);

        navx = AHRS.getInstance(hwMap.get(NavxMicroNavigationSensor.class,
                "navx"), AHRS.DeviceDataType.kProcessedData);

        driveProfile = new PointMotionProfile(DRIVE_PROFILE_SPEED);
        turnProfile = new MotionProfile(TURN_PROFILE_SPEED, TURN_PROFILE_MAX);
        turnController = new SimpleFeedbackController(AUTO_ALIGN_P);
    }

    /**
     * Drives the robot field oriented
     *
     * @param driveInput the (x, y) input
     * @param turn the turning input
     * @param autoAlign whether to autoAlign
     * @param lowGear whether to put the robot to virtual low gear
     */
    public void drive(Point driveInput, double turn, boolean autoAlign, boolean lowGear) {
        turn = turnProfile.calculate(autoAlign ? turnToAngle() : turn);

        driveInput = driveProfile.calculate(
                driveInput.restrictMagnitude(lowGear ? VIRTUAL_LOW_GEAR : VIRTUAL_HIGH_GEAR));
        double power = Range.clip(driveInput.magnitude(), 0.0, 1.0);
        double angle = driveInput.angle();

        leftDrive.setPower(turn + power * Math.cos(angle + LEFT_DRIVE_OFFSET - pose.angle));
        rightDrive.setPower(turn + power * Math.cos(angle + RIGHT_DRIVE_OFFSET - pose.angle));
        backDrive.setPower(turn + power * Math.cos(angle + BACK_DRIVE_OFFSET - pose.angle));
    }

    private double turnToAngle() {
        double error = Angles.clipRadians(pose.angle - targetHeading);
        return Math.abs(error) < AUTO_ALIGN_ERROR ? 0.0 : turnController.calculate(error);
    }

    /**
     * Sets the auto-alignment target heading
     *
     * @param targetHeading the target heading in radians
     */
    public void setTargetHeading(double targetHeading) {
        this.targetHeading = targetHeading;
    }

    /**
     * Updates Robot Pose using Odometry Wheels
     */
    public void update() {
        int currentLeft = leftOdometry.getCurrentPosition();
        int currentRight = rightOdometry.getCurrentPosition();
        int currentFront = frontOdometry.getCurrentPosition();

        double deltaX = (currentLeft - previousLeftPosition + currentRight - previousRightPosition) *
                INCHES_PER_TICK * STRAFE_ODOMETRY_CORRECTION;
        double deltaY = (currentFront - previousFrontPosition) *
                INCHES_PER_TICK * FORWARD_ODOMETRY_CORRECTION;

        pose.angle = imuOffset + getCorrectedYaw();

        double averagedHeading = Angles.average(pose.angle, previousHeading);

        pose.x += deltaX * Math.sin(averagedHeading) + deltaY * Math.cos(averagedHeading);
        pose.y += -deltaX * Math.cos(averagedHeading) + deltaY * Math.sin(averagedHeading);

        previousLeftPosition = currentLeft;
        previousRightPosition = currentRight;
        previousFrontPosition = currentFront;
        previousHeading = pose.angle;
    }

    /**
     * Sets the Robot Pose
     *
     * @param newPose the new robot pose
     */
    public void setPose(RobotPose newPose) {
        pose.x = newPose.x;
        pose.y = newPose.y;

        double yaw = getCorrectedYaw();
        imuOffset = newPose.angle - yaw;
        pose.angle = imuOffset + yaw;
        previousHeading = pose.angle;
    }

    private double getCorrectedYaw() {
        return Math.toRadians(-navx.getYaw());
    }

    /**
     * Sets the Drive Wheels to Float Mode
     */
    public void setFloat() {
        leftDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        rightDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        backDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Get Motor Velocities
     *
     * @return the left, right, back velocities
     */
    public double[] getMotorVelocities() {
        return new double[]{
                leftDrive.getVelocity(),
                rightDrive.getVelocity(),
                backDrive.getVelocity(),
        };
    }

    /**
     * Get the dead wheel position values for telemetry
     *
     * @return the left, right, front dead wheel positions
     */
    public double[] getOdometryPositions() {
        return new double[]{previousLeftPosition, previousRightPosition, previousFrontPosition};
    }

    /**
     * The current robot pose
     *
     * @return the Robot pose (x, y, theta)
     */
    public RobotPose getRobotPose() {
        return pose;
    }
}