package org.firstinspires.ftc.teamcode.auto.competition_autos;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.constants.ArmConstants;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.subsystems.Lights;
import org.firstinspires.ftc.teamcode.constants.*;
import org.firstinspires.ftc.teamcode.math.*;

import java.util.List;

/**
 * Sample Auto to Copy Paste Edit with
 */
@Autonomous(name = "Blue Auto")
public class BlueAuto extends LinearOpMode implements DrivetrainConstants, ArmConstants {
    /**
     * Runs the Auto routine
     */
    @Override
    public void runOpMode() {
        ElapsedTime waitTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

        ElapsedTime sampleTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        Drivetrain drivetrain = new Drivetrain(hardwareMap, 36, -55.25, Math.PI / 2);
        Arm arm = new Arm(hardwareMap, 0, 0);
        Hand hand = new Hand(hardwareMap);
        PropDetector propDetector = new PropDetector(hardwareMap);
        List<Recognition> recs;

        Lights lights = new Lights(hardwareMap, false);
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GRAY);


        double[][] points2 = new double[][]{{35.7, -61.5}, {59.5, -34.8}, {48.5, 39.6}, {-55.0, -35.3}};
        double[][] points1 = new double[][]{{35.8, -62.8}, {35.8, -62.8}, {35.4, -40.6}, {35.4, -40.6}};

        Spline spline2 = new Spline(points2, 20, drivetrain, 100);
        Spline spline1 = new Spline (points1, 20, drivetrain, 100);
        spline2.update();
        spline1.update();

        PropDetector.PropLocation propPosition = null;
        while (opModeInInit()) {
            propPosition = propDetector.getPropPosition();
            telemetry.addData("prop position", propPosition);
            telemetry.update();
        }


        waitForStart();


        propDetector.close();
        AprilTagVision vision = new AprilTagVision(hardwareMap);


        double purplePixelAngle;

        if (propPosition != null) {
            if (propPosition == PropDetector.PropLocation.CENTER) {
                purplePixelAngle = 75.0;
            } else if (propPosition == PropDetector.PropLocation.RIGHT) {
                purplePixelAngle = 55.0;
            }
        } else {
            purplePixelAngle = 175.0;
        }

        while (spline1.desiredT() < 0.98) {
            spline1.update();
            double[] robotPose = new double[]{drivetrain.getRobotPose().x, drivetrain.getRobotPose().y};

            double jamesSplineError = Math.hypot(robotPose[0] - points2[3][0], robotPose[1] - points2[3][1]);

            double turn = 0;
            boolean autoAlign = true;

            double desiredT = spline2.desiredT();

            drivetrain.drive(new Vector(Math.cos(spline1.angle()), Math.sin(spline1.angle())), turn, autoAlign, true);
            if (sampleTime.time() > 20) {
                spline1.update();
                sampleTime.reset();
            }

        }

        waitTime.reset();
        while (waitTime.time() < 2) {
            drivetrain.update();
            drivetrain.drive(new Vector(0, 0), 0, true, false);
            if (propPosition == PropDetector.PropLocation.RIGHT)
                drivetrain.setTargetHeading(1);
            else if (propPosition == PropDetector.PropLocation.CENTER)
                drivetrain.setTargetHeading(Math.PI / 2 + 0.2);
            else
                drivetrain.setTargetHeading(Math.PI - 0.2);

        }

        while (spline2.desiredT() < 0.98) {
            spline2.update();
            double[] robotPose = new double[]{drivetrain.getRobotPose().x, drivetrain.getRobotPose().y};

            double jamesSplineError = Math.hypot(robotPose[0] - points2[3][0], robotPose[1] - points2[3][1]);
            double desiredT = spline2.desiredT();

            drivetrain.setTargetHeading(desiredT < 0.9 ? 0 : Math.PI);

            if(desiredT > 0.9){
                arm.setTargetArmPosition(625);
                arm.armToPosition();
                arm.setTargetWristPosition(4700);
                arm.wristToPosition();

            }            double turn = 0;
            boolean autoAlign = true;

            drivetrain.drive(new Vector(Math.cos(spline2.angle()), Math.sin(spline2.angle())), turn, autoAlign, true);
            if (sampleTime.time() > 20) {
                spline2.update();
                sampleTime.reset();
            }
        }
        waitTime.reset();
        while(waitTime.time() < 2) {
            drivetrain.drive(new Vector(0, 0), 0, true, true);
            drivetrain.update();
        }

        hand.toggleLeft();
        hand.toggleRight();

        waitTime.reset();
        while (waitTime.time() < 2) {
            drivetrain.update();
        }
        waitTime.reset();
        while (waitTime.time() < 0.5) {
            drivetrain.update();
            drivetrain.drive(new Vector(0.5, 0), 0, false, false);
        }

        waitTime.reset();
        while (waitTime.time() < 2) {
            drivetrain.update();
            drivetrain.drive(new Vector(0, 0), 0, false, false);
        }

        terminateOpModeNow();
    }
}