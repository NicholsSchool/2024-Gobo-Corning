package org.firstinspires.ftc.teamcode.teleops.test_teleops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.math.MotionProfile;
import org.firstinspires.ftc.teamcode.math.Point;
import org.firstinspires.ftc.teamcode.math.PointMotionProfile;

/**
 * Testing Teleop for Motion Profiles
 */
@Config
@TeleOp(name="Profile Testing", group="Testing")
public class ProfileTeleop extends OpMode {
    private ElapsedTime loopTime;
    private Controller controller;
    private MotionProfile motionProfile;
    private PointMotionProfile pointMotionProfile;
    private FtcDashboard dashboard;
    public static boolean clip;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        controller = new Controller(gamepad1);
        motionProfile = new MotionProfile(0.5, 0.5);
        pointMotionProfile = new PointMotionProfile(0.5);
        loopTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
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

        Point leftStick = controller.leftStick.toPoint();

        if(clip)
            leftStick.clipMagnitude(0.5);
        leftStick = pointMotionProfile.calculate(leftStick);

        TelemetryPacket packet = new TelemetryPacket(false);
        packet.fieldOverlay()
                .setScale(2.0, 2.0)
                .setRotation(Math.PI)
                .drawGrid(0.0, 0.0, 2.0, 2.0, 21, 21)
                .setFill("red")
                .fillCircle(leftStick.x, leftStick.y, 0.05);
        dashboard.sendTelemetryPacket(packet);

        double leftTrigger = motionProfile.calculate(controller.leftTrigger.value());

        telemetry.addData("left trigger", leftTrigger);

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