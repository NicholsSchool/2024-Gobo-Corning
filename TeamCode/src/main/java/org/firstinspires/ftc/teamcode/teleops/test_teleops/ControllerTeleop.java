package org.firstinspires.ftc.teamcode.teleops.test_teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.Controller;

/**
 * Testing Teleop for Controller
 */
@Config
@TeleOp(name="Controller Testing", group="Testing")
public class ControllerTeleop extends OpMode {
    private ElapsedTime loopTime;
    private Controller controller;
    private boolean squareToggle;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        loopTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        controller = new Controller(gamepad1);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
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

        squareToggle = controller.square.wasJustPressed() != squareToggle;

        telemetry.addData("BUTTONS:", "");
        telemetry.addData("x", controller.x);
        telemetry.addData("circle", controller.circle);
        telemetry.addData("square toggle", squareToggle);
        telemetry.addData("triangle", controller.triangle);
        telemetry.addData("options", controller.options);
        telemetry.addData("share", controller.share);

        telemetry.addData("AXES:", "");
        telemetry.addData("left trigger", controller.leftTrigger);
        telemetry.addData("right trigger is zero", controller.rightTrigger.hasBeenZero());

        telemetry.addData("JOYSTICKS:", "");
        telemetry.addData("left joystick", controller.leftStick);
        telemetry.addData("right joystick", controller.rightStick);

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