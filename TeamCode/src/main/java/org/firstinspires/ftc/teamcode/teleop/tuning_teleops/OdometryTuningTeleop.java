package org.firstinspires.ftc.teamcode.teleop.tuning_teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.math.Angles;
import org.firstinspires.ftc.teamcode.math.RobotPose;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Teleop for Tuning Odometry
 */
@Config
@TeleOp(name="Odometry Tuning", group="Tuning")
public class OdometryTuningTeleop extends OpMode {
    private Drivetrain drivetrain;
    private Controller controller;
    private ElapsedTime loopTime;
    private FtcDashboard dashboard;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        controller = new Controller(gamepad1);
        drivetrain = new Drivetrain(hardwareMap, 0.0, 0.0, Angles.PI_OVER_TWO);
        drivetrain.setFloat();
        loopTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /**
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        controller.update();

        drivetrain.update();

        RobotPose pose = drivetrain.getRobotPose();
        telemetry.addData("x", pose.x);
        telemetry.addData("y", pose.y);

        double[] odometryRaw = drivetrain.getOdometryPositions();
        telemetry.addData("left raw ticks", odometryRaw[0]);
        telemetry.addData("right raw ticks", odometryRaw[1]);
        telemetry.addData("front raw ticks", odometryRaw[2]);

        telemetry.addData("loop time", loopTime.time());
        loopTime.reset();
        telemetry.update();
    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}