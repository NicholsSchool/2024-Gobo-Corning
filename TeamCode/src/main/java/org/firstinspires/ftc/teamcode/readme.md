        THINGS TO REMEMBER:
Don't check for alliance in looped methods
Record the loop times of each optimized teleop

        CODE CHANGES:
Finish BezierSpline (use constants in constructor)              [TODO]
Spline Drivetrain pointer works??? (change both if not)         [TODO]
Verify whether the Navx pitch is upside down (probably is)      [TODO]
Choose highest allowed cam resolution in both subsystems        [TODO]
Should I put the wrist to float for ground pickup?              [TODO]
Potentiometer Switch for choosing l/r column in auto            [TODO]

        CLASS TESTING:
Fully Test Controllers                  [FINISHED]
Fully Test Angles                       [TODO]
Fully Test Point                        [TODO]
Fully Test RobotPose                    [TODO]
Fully Test MotionProfile                [FINISHED]
Fully Test PointMotionProfile           [FINISHED]
Fully Test SimpleFeedbackController     [TODO]
Fully Test FeedbackController           [TODO]
Fully Test ParabolicSpline              [TODO]
Fully Test BezierSpline                 [TODO]
Fully Test Hand                         [TODO]
Fully Test Lights                       [TODO]
Fully Test AprilTagVision               [TODO]
Fully Test TensorFlowVision             [TODO]
Fully Test Arm                          [TODO]
Fully Test Drivetrain                   [TODO]

        CONSTANTS TUNING:
ArmConstants - tune PlaneLauncher range         [TODO]
ArmConstants - tune shoulder max power          [TODO]
ArmConstants - tune climb max power             [TODO]
ArmConstants - tune shoulder p                  [TODO]
ArmConstants - tune shoulder vertical p         [TODO]
ArmConstants - tune arm vertical position       [TODO]
ArmConstants - tune Climb p                     [TODO]
ArmConstants - tune Target Pitch                [TODO]
ArmConstants - tune wrist max power             [TODO]
ArmConstants - tune wrist p                     [TODO]
ArmConstants - tune wrist fourbar positions     [TODO]
ArmConstants - tune fourbar switching angle     [TODO]

ControllerConstants - tune deadband             [TODO]
ControllerConstants - tune wait time            [TODO]

DrivetrainConstants - Tune Profile Speeds       [TODO]
DrivetrainConstants - Tune Turning Max          [TODO]
DrivetrainConstants - Tune virtual gears        [TODO]
DrivetrainConstants - Tune auto align p         [TODO]
DrivetrainConstants - Tune auto align error     [TODO]
DrivetrainConstants - tune Drive Motor FF       [TODO]
DrivetrainConstants - Tune odometry corrections [TODO]

HandConstants - tune all Grabber ranges         [TODO]

ParabolicSplineConstants - Tune Spline P        [TODO]
ParabolicSplineConstants - Tune Spline error    [TODO]
ParabolicSplineConstants - Tune ALL waypoints   [TODO]

BezierSplineConstants - Tune Estimation Values  [TODO]

VisionConstants - tune camera offsets           [TODO]
VisionConstants - tune BOTH thresholds          [TODO]