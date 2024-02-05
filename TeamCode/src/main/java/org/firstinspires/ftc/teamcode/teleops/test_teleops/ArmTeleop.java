package org.firstinspires.ftc.teamcode.teleops.test_teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

/**
 * Teleop for the Arm
 */
@Config
@TeleOp(name="Arm Testing", group="Testing")
public class ArmTeleop extends OpMode {
    private Arm arm;
    private Controller controller;
    private ElapsedTime loopTime;
    private FtcDashboard dashboard;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        arm = new Arm(hardwareMap);
        arm.setArmFloat();
        arm.setWristFloat();

        controller = new Controller(gamepad1);
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

        if(controller.share.wasJustPressed())
            arm.togglePlane();

        arm.wristManual(controller.leftStick.x.value());

        telemetry.addData("wrist position", arm.getWristPosition());

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