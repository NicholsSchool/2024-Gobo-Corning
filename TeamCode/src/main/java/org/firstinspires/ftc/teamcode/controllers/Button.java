package org.firstinspires.ftc.teamcode.controllers;

import androidx.annotation.NonNull;

/**
 * A Controller Button
 */
public class Button {
    private boolean isPressed;
    private boolean wasJustPressed;
    private boolean toggleState;

    /**
     * Instantiates the Button
     */
    public Button() {}

    /**
     * The button's current state
     *
     * @return whether the button is pressed
     */
    public boolean isPressed() {
        return isPressed;
    }

    /**
     * The button's change of state
     *
     * @return whether the button was just pressed
     */
    public boolean isWasJustPressed() {
        return wasJustPressed;
    }

    /**
     * The button's toggle state
     *
     * @return whether the toggle is true
     */
    public boolean toggleState() {
        return toggleState;
    }

    /**
     * The Button as a String
     *
     * @return whether the Button is pressed
     */
    @NonNull
    public String toString() {
        return String.valueOf(isPressed);
    }

    /**
     * Updates the Button
     *
     * @param isNowPressed the new state
     */
    public void update(boolean isNowPressed) {
        wasJustPressed = isNowPressed && !isPressed;
        toggleState = wasJustPressed != toggleState;
        isPressed = isNowPressed;
    }
}