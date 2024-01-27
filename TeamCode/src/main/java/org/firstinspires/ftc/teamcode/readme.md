        THINGS TO REMEMBER:
Add a plane spline path, change teleop spline paths
Add James's spline Math too
Check the Navx pitch isn't upside down
Use all utility classes in every possible spot

        CODE CHANGES TODO:
Remove all alliance checking in loop methods
Magnitude scaling if current magnitude is 0???
Figure out a way to optimize Angles.averageAngles()
Check loop times of each optimized teleop

        CONSTANTS TUNING:
ArmConstants - tune PlaneLauncher range
ArmConstants - tune shoulder max power
ArmConstants - tune climb max power
ArmConstants - tune shoulder p
ArmConstants - tune shoulder vertical p
ArmConstants - tune arm vertical position
ArmConstants - tune Climb p
ArmConstants - tune Target Pitch
ArmConstants - tune wrist max power
ArmConstants - tune wrist p
ArmConstants - tune wrist fourbar positions
ArmConstants - tune fourbar switching angle

ControllerConstants - tune deadband
ControllerConstants - tune wait time

DrivetrainConstants - Tune Profile Speeds
DrivetrainConstants - Tune Turning Max
DrivetrainConstants - Tune Low and High Gear max
DrivetrainConstants - Tune auto align p
DrivetrainConstants - Tune auto align error
DrivetrainConstants - tune Drive Motor FF (don't zero the defaults for P, I, D)
DrivetrainConstants - Tune both odometry corrections

HandConstants - tune all Grabber ranges

ParabolicSplineConstants - Tune ALL Waypoints

VisionConstants - tune camera offsets
VisionConstants - tune detection threshold