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


        double[][] points1 = new double[][]{{35.8, -62.8}, {35.8, -62.8}, {35.4, -40.6}, {35.4, -34.6}};
        double[][] points2 = new double[][]{{36.0, -38.0}, {47.4, -69.6}, {76.8, -22.1}, {38.8, -4.2}};
        double[][] points3 = new double[][]{{38.8, -4.2}, {-22.4, -9.7}, {-45.7, 7.8}, {-44.0, -34.5}};

        Spline spline1 = new Spline (points1, 20, drivetrain, 100);
        Spline spline2 = new Spline(points2, 20, drivetrain, 100);
        Spline spline3 = new Spline(points3, 20, drivetrain, 100);
        spline1.update();
        spline2.update();
        spline3.update();

        PropDetector.PropLocation propPosition = null;
        while (opModeInInit()) {
            propPosition = propDetector.getPropPosition();
            telemetry.addData("prop position", propPosition);
            telemetry.update();
        }


        waitForStart();


        propDetector.close();
        AprilTagVision vision = new AprilTagVision(hardwareMap);


        double purplePixelAngle = Math.PI / 2;
        double swipeHeading = Math.PI / 2;

        if (propPosition != null) {
            if (propPosition == PropDetector.PropLocation.CENTER) {
                purplePixelAngle = 0;
                swipeHeading = Math.PI / 2;
            } else if (propPosition == PropDetector.PropLocation.RIGHT) {
                purplePixelAngle = 0;
                swipeHeading = 0.7;
            }else if(propPosition == PropDetector.PropLocation.LEFT){
                purplePixelAngle = Math.PI;
                swipeHeading = Math.PI - 0.5;
            }
        } else {
            purplePixelAngle = 0;
            swipeHeading = Math.PI / 2;
        }

        while (spline1.desiredT() < 0.98) {
            spline1.update();
            double[] robotPose = new double[]{drivetrain.getRobotPose().x, drivetrain.getRobotPose().y};

            double jamesSplineError = Math.hypot(robotPose[0] - points2[3][0], robotPose[1] - points2[3][1]);

            arm.setTargetArmPosition(600);
            arm.armToPosition();

            arm.setTargetWristPosition(4200);
            arm.wristToPosition();

            telemetry.addData("wrist", arm.getWristPosition());
            telemetry.update();

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
            drivetrain.setTargetHeading(purplePixelAngle);

            arm.setTargetWristPosition(4200);
            arm.wristToPosition();

        }

        waitTime.reset();
        while(waitTime.time() < 2){
            drivetrain.setTargetHeading(swipeHeading);

            arm.setTargetArmPosition(-500);
            arm.armToPosition();

            arm.setTargetWristPosition(4200);
            arm.wristToPosition();

            drivetrain.update();
            drivetrain.drive(new Vector(0, 0), 0, true, false);
        }

        hand.toggleRight();

        arm.offsetEncoders();
        arm.setTargetArmPosition(0);
        arm.setTargetWristPosition(0);

        while (spline2.desiredT() < 0.98) {
            spline2.update();
            double[] robotPose = new double[]{drivetrain.getRobotPose().x, drivetrain.getRobotPose().y};

            arm.setTargetWristPosition(1000);
            arm.wristToPosition();

            if(spline2.desiredT() > 0.2) {
                arm.setTargetArmPosition(500);
                arm.armToPosition();
            }

            double jamesSplineError = Math.hypot(robotPose[0] - points2[3][0], robotPose[1] - points2[3][1]);
            double desiredT = spline2.desiredT();

            drivetrain.setTargetHeading(Math.PI);
            double turn = 0;
            boolean autoAlign = true;


            telemetry.addData("wrist", arm.getWristPosition());
            telemetry.update();

            drivetrain.drive(new Vector(Math.cos(spline2.angle()), Math.sin(spline2.angle())), turn, autoAlign, true);
            if (sampleTime.time() > 20) {
                spline2.update();
                sampleTime.reset();
            }
        }


        while (spline3.desiredT() < 0.98) {
            spline3.update();
            double[] robotPose = new double[]{drivetrain.getRobotPose().x, drivetrain.getRobotPose().y};

            double jamesSplineError = Math.hypot(robotPose[0] - points2[3][0], robotPose[1] - points2[3][1]);
            double desiredT = spline3.desiredT();

            arm.setTargetArmPosition(0);
            arm.armToPosition();

            drivetrain.setTargetHeading(desiredT < 0.9 ? 0 : Math.PI);
            double turn = 0;
            boolean autoAlign = true;

            drivetrain.drive(new Vector(Math.cos(spline3.angle()), Math.sin(spline3.angle())), turn, autoAlign, true);
            if (sampleTime.time() > 20) {
                spline3.update();
                sampleTime.reset();
            }
        }

        waitTime.reset();
        while(waitTime.time() < 0.5) {
            drivetrain.setTargetHeading(Math.PI / 2);
            drivetrain.drive(new Vector(0, 0), 0, true, true);
            drivetrain.update();
        }

        waitTime.reset();
        while (waitTime.time() < 1) {
            drivetrain.update();
            drivetrain.drive(new Vector(0, 0), 0, true, true);
        }

        terminateOpModeNow();
    }
}