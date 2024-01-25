package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.math.Point;

/**
 * A Controller
 */
public class Controller {
    private final Gamepad gamepad;
    public Button leftBumper;
    public Button rightBumper;
    public Button dpadUp;
    public Button dpadDown;
    public Button dpadLeft;
    public Button dpadRight;
    public Button x;
    public Button circle;
    public Button square;
    public Button triangle;
    public Button share;
    public Button options;
    public Button leftStick;
    public Button rightStick;

    public Axis leftTrigger;
    public Axis rightTrigger;
    public Axis leftStickX;
    public Axis leftStickY;
    public Axis rightStickX;
    public Axis rightStickY;

    /**
     * Instantiates the Controller
     *
     * @param gamepad the gamepad to use
     */
    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;

        leftBumper = new Button();
        rightBumper = new Button();
        dpadUp = new Button();
        dpadDown = new Button();
        dpadLeft = new Button();
        dpadRight = new Button();
        x = new Button();
        circle = new Button();
        square = new Button();
        triangle = new Button();
        share = new Button();
        options = new Button();
        leftStick = new Button();
        rightStick = new Button();

        leftTrigger = new Axis();
        rightTrigger = new Axis();
        leftStickX = new Axis();
        leftStickY = new Axis();
        rightStickX = new Axis();
        rightStickY = new Axis();
    }

    /**
     * Updates all Buttons and Axes
     */
    public void update() {
        leftBumper.update(gamepad.left_bumper);
        rightBumper.update(gamepad.right_bumper);
        dpadUp.update(gamepad.dpad_up);
        dpadDown.update(gamepad.dpad_down);
        dpadLeft.update(gamepad.dpad_left);
        dpadRight.update(gamepad.dpad_right);
        x.update(gamepad.a);
        circle.update(gamepad.b);
        square.update(gamepad.x);
        triangle.update(gamepad.y);
        share.update(gamepad.back);
        options.update(gamepad.start);
        leftStick.update(gamepad.left_stick_button);
        rightStick.update(gamepad.right_stick_button);

        leftStickX.update(gamepad.left_stick_x);
        leftStickY.update(-gamepad.left_stick_y);
        rightStickX.update(gamepad.right_stick_x);
        rightStickY.update(-gamepad.right_stick_y);
        leftTrigger.update(gamepad.left_trigger);
        rightTrigger.update(gamepad.right_trigger);
    }

    /**
     * The Point value of the left joystick
     *
     * @return the joystick value
     */
    public Point leftJoystick() {
        return new Point(leftStickX.value(), leftStickY.value());
    }
}