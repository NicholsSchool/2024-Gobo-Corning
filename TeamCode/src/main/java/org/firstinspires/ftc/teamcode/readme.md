        THINGS TO REMEMBER:
Don't check for alliance in looped methods
Record the loop times of each optimized teleop

        CODE CHANGES TODO:
Finish BezierSpline with James (set defaults for BezierSpline constructor using constants)
Check whether the spline Drivetrain instances update automatically (change both classes if not)
Verify whether the Navx pitch is upside down (probably is)
When camera is switched, check what the allowed resolutions are... choose the highest (in both subsystems)
Should I put the wrist to float for ground pickup?
Add code for Potentiometer Switch for choosing column in auto

        CLASS TESTING:
Fully Test Controllers
Fully Test Angles
Fully Test Point
Fully Test RobotPose
Fully Test MotionProfile
Fully Test PointMotionProfile
Fully Test SimpleFeedbackController
Fully Test FeedbackController
Fully Test ParabolicSpline
Fully Test BezierSpline
Fully Test Hand
Fully Test Lights
Fully Test AprilTagVision
Fully Test TensorFlowVision
Fully Test Arm
Fully Test Drivetrain

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
DrivetrainConstants - tune Drive Motor FF
DrivetrainConstants - Tune both odometry corrections

HandConstants - tune all Grabber ranges

ParabolicSplineConstants - Tune Spline P
ParabolicSplineConstants - Tune Spline error
ParabolicSplineConstants - Tune ALL waypoint values

BezierSplineConstants - Tune Estimation Values

VisionConstants - tune camera offsets
VisionConstants - tune BOTH detection thresholds