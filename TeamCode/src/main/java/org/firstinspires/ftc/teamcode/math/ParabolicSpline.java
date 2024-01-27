package org.firstinspires.ftc.teamcode.math;

import org.firstinspires.ftc.teamcode.constants.ParabolicSplineConstants;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Math for Parabolic Spline Paths
 */
public class ParabolicSpline implements ParabolicSplineConstants {
    private final Drivetrain drivetrain;
    private final double WAYPOINT_Y;
    private final Point INTAKE;

    /**
     * Instantiates the ParabolicSpline
     *
     * @param drivetrain the drivetrain
     * @param isBlueAlliance whether we are blue alliance
     */
    public ParabolicSpline(Drivetrain drivetrain, boolean isBlueAlliance) {
        this.drivetrain = drivetrain;
        WAYPOINT_Y = isBlueAlliance ? BLUE_WAYPOINT_Y : RED_WAYPOINT_Y;
        INTAKE = new Point(INTAKE_X, isBlueAlliance ? BLUE_INTAKE_Y : RED_INTAKE_Y);

    }

    /**
     * With the robot at (rx, ry), calculates the drive vector of the robot
     * in order to follow a parabola and arrive at the waypoint (wx, wy)
     * that is the parabola's vertex.
     * The parabola is defined to contain the robot
     *
     * @param robot the robot (rx, ry)
     * @param waypoint the waypoint (wx, wy)
     * @param toIntake whether the robot is going to the intake
     *
     * @return the drive vector in [x, y] notation
     */
    public Point vectorToVertex(Point robot, Point waypoint, boolean toIntake) {
        if(robot.x == waypoint.x)
            return toIntake ? new Point(1.0, 0.0) : new Point(-1.0, 0.0);

        return new Point(waypoint.x - robot.x, (waypoint.y - robot.y) * 2.0);
    }

    /**
     * With the robot at (x, y), calculates the drive vector of the robot
     * in order to follow a parabola and arrive at the waypoint (wx, wy).
     * The parabola is defined with its vertex constrained to the x-value
     * of h (the previous waypoint), and the curve consists of both the
     * waypoint and robot coordinates.
     *
     * @param robot the robot (rx, ry)
     * @param waypoint the waypoint (wx, wy)
     * @param h  the x value of the previous waypoint
     * @param toIntake whether the robot is going to the intake
     * @return the drive vector in [x, y] notation
     */
    public Point vectorFromVertex(Point robot, Point waypoint, double h, boolean toIntake) {
        if(robot.x == waypoint.x)
            return toIntake ? new Point(1.0, 0.0) : new Point(-1.0, 0.0);

        double robotDistSquared = Math.pow(robot.x - h, 2);
        double waypointDistSquared = Math.pow(waypoint.x - h, 2);

        if(robotDistSquared == waypointDistSquared)
            return robot.y < waypoint.y ? new Point(0.0, 1.0) : new Point(0.0, -1.0);

        double k = (waypoint.y * robotDistSquared - robot.y * waypointDistSquared)
                / (robotDistSquared - waypointDistSquared);

        return (robot.x > waypoint.x) == toIntake ?
                new Point(h - robot.x, (k - robot.y) * 2.0) :
                new Point(robot.x - h, (robot.y - k) * 2.0);
    }

    /**
     * Automatically directs the robot to the Coordinates of the Correct Intake
     * area using parabolas in piecewise.
     *
     * @param turn the turn speed proportion
     * @param autoAlign whether to autoAlign
     * @param lowGear whether to drive in low gear
     */
    public void splineToIntake(double turn, boolean autoAlign, boolean lowGear) {
        Point robot = drivetrain.getRobotPose().toPoint();

        Point drive;
        if(robot.x < LEFT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(LEFT_WAYPOINT_X, WAYPOINT_Y), true);
        else if(robot.x < RIGHT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(RIGHT_WAYPOINT_X, WAYPOINT_Y), true);
        else
            drive = vectorFromVertex(robot, INTAKE, RIGHT_WAYPOINT_X, true);

        double distance = robot.distance(INTAKE);
        drive.scaleMagnitude((distance >= SPLINE_ERROR ? SPLINE_P * distance : 0.0));

        drivetrain.drive(drive, turn, autoAlign, lowGear);
    }

    /**
     * Automatically directs the robot to the Coordinates of the Correct Backstage
     * area using parabolas in piecewise.
     *
     * @param turn the turn speed proportion
     * @param autoAlign whether to autoAlign
     * @param scoringY the Y value to end at
     * @param lowGear whether to drive in low gear
     */
    public void splineToScoring(double turn, boolean autoAlign, double scoringY, boolean lowGear) {
        Point robot = drivetrain.getRobotPose().toPoint();
        Point scoring = new Point(SCORING_X, scoringY);

        Point drive;
        if(robot.x > RIGHT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(RIGHT_WAYPOINT_X, CENTER_WAYPOINT_Y), false);
        else if(robot.x > LEFT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(LEFT_WAYPOINT_X, CENTER_WAYPOINT_Y), false);
        else
            drive = vectorFromVertex(robot, scoring, LEFT_WAYPOINT_X, false);

        double distance = robot.distance(scoring);
        drive.scaleMagnitude((distance >= SPLINE_ERROR ? SPLINE_P * distance : 0.0));

        drivetrain.drive(drive, turn, autoAlign, lowGear);
    }

    /**
     * Automatically directs the robot to the X value for Launching the plane
     *
     * @param turn the turn speed proportion
     * @param autoAlign whether to autoAlign
     * @param lowGear whether to drive in low gear
     */
    public void splineToPlane(double turn, boolean autoAlign, boolean lowGear) {
        Point robot = drivetrain.getRobotPose().toPoint();

        Point drive;
        if(robot.x > RIGHT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(RIGHT_WAYPOINT_X, CENTER_WAYPOINT_Y), false);
        else if(robot.x > LEFT_WAYPOINT_X)
            drive = vectorToVertex(robot, new Point(LEFT_WAYPOINT_X, CENTER_WAYPOINT_Y), false);
        else
            drive = robot.x >= PLANE_LAUNCHING_X ? new Point(-1.0, 0.0) : new Point(1.0, 0.0);

        double distance = Math.abs(robot.x - PLANE_LAUNCHING_X);
        drive.scaleMagnitude((distance >= SPLINE_ERROR ? SPLINE_P * distance : 0.0));

        drivetrain.drive(drive, turn, autoAlign, lowGear);
    }
}