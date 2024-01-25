package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.constants.HandConstants;

/**
 * The Robot Hand
 */
public class Hand implements HandConstants {
    private final Servo leftGrabber;
    private final Servo rightGrabber;
    private boolean leftPosition;
    private boolean rightPosition;

    /**
     * Initializes the Hand
     */
    public Hand(HardwareMap hardwareMap) {
        leftGrabber = hardwareMap.get(Servo.class, "leftGrabber");
        leftGrabber.setDirection(Servo.Direction.FORWARD);
        leftGrabber.scaleRange(HandConstants.LEFT_IN, LEFT_OUT);
        leftGrabber.setPosition(1.0);

        rightGrabber = hardwareMap.get(Servo.class, "rightGrabber");
        rightGrabber.setDirection(Servo.Direction.FORWARD);
        rightGrabber.scaleRange(HandConstants.RIGHT_OUT, RIGHT_IN);
        rightGrabber.setPosition(0.0);

        leftPosition = true;
        rightPosition = true;
    }

    /**
     * Toggles the left grabber
     * */
    public void toggleLeft() {
        leftGrabber.setPosition(leftPosition ? 0.0 : 1.0);
        leftPosition = ! leftPosition;
    }

    /**
     * Toggles the right grabber
     * */
    public void toggleRight() {
        rightGrabber.setPosition(leftPosition ? 1.0 : 0.0);
        rightPosition = !rightPosition;
    }
}