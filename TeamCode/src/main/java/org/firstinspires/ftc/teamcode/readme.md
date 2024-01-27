        THINGS TO REMEMBER:
Check that all utility classes are used in every possible spot
NO STORING ALLIANCE FOR LOOP USE IN ANY OBJECT
Check loop times of each optimized teleop
When camera is switched, set Camera Resolution to 1920... in the Vision Portal Build
Throttle loop times for consistency?

        CODE CHANGES TODO:
Figure out a way to optimize Angles.averageAngles()
Add BezierSpline too (and BezierSplineConstants)
Check whether the Navx pitch is upside down (probably is)

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