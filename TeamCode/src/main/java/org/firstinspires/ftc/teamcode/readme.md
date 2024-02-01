        THINGS TO REMEMBER:
Don't check for alliance in looped methods
Record the loop times of each optimized teleop

        CODE CHANGES:
Spline Drivetrain pointer works??? (change both if not)         [TODO]
Verify that the Navx pitch is upside down (probably is)         [TODO]
Choose highest allowed camera resolution in both subsystems     [TODO]
Should I put the wrist to float for ground pickup?              [TODO]
Potentiometer Switch for choosing l/r column in auto            [TODO]
Add Kalman filter for continuous April Tags???                  [TODO]

        CLASS TESTING:
Fully Test Controllers                  [FINISHED]
Fully Test Angles                       [FINISHED]
Fully Test Point                        [FINISHED]
Fully Test Vector                       [FINISHED]
Fully Test RobotPose                    [FINISHED]
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

ControllerConstants - tune deadband             [FINISHED]
ControllerConstants - tune wait time            [TODO]

DrivetrainConstants - Tune Profile Speeds       [TODO]
DrivetrainConstants - Tune Turning Max          [TODO]
DrivetrainConstants - Tune virtual gears        [TODO]
DrivetrainConstants - Tune auto align p         [TODO]
DrivetrainConstants - Tune auto align error     [TODO]
DrivetrainConstants - tune Drive Motor FF       [TODO]
DrivetrainConstants - Tune odometry corrections [TODO]

HandConstants - tune all Grabber ranges         [TODO]

SplineConstants - Tune Spline P                 [TODO]
SplineConstants - Tune Spline error             [TODO]
SplineConstants - Tune ALL waypoints            [TODO]
SplineConstants - Make and Tune Bezier Values   [TODO]

VisionConstants - tune camera offsets           [TODO]
VisionConstants - tune BOTH thresholds          [TODO]