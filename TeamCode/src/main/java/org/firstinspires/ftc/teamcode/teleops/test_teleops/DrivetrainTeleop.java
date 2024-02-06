package org.firstinspires.ftc.teamcode.teleops.test_teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.math.Angles;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Teleop for the Drivetrain
 */
@Config
@TeleOp(name="Drivetrain Testing", group="Testing")
public class DrivetrainTeleop extends OpMode {
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
        drivetrain = new Drivetrain(hardwareMap, 0.0, 0.0, Math.PI / 2.0);
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

        if(controller.dpadUp.wasJustPressed())
            drivetrain.setTargetHeading(Angles.PI_OVER_TWO);
        else if(controller.dpadDown.wasJustPressed())
            drivetrain.setTargetHeading(Angles.NEGATIVE_PI_OVER_TWO);
        else if(controller.dpadRight.wasJustPressed())
            drivetrain.setTargetHeading(0.0);
        else if(controller.dpadLeft.wasJustPressed())
            drivetrain.setTargetHeading(Math.PI);

        drivetrain.drive(
                controller.leftStick.toVector(),
                controller.rightStick.x.value(),
                controller.rightTrigger.value() > 0.0,
                controller.leftTrigger.value() <= 0.0);

        double[] motorVelocities = drivetrain.getMotorVelocities();
        telemetry.addData("left vel", motorVelocities[0]);
        telemetry.addData("right vel", motorVelocities[1]);
        telemetry.addData("back vel", motorVelocities[2]);

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