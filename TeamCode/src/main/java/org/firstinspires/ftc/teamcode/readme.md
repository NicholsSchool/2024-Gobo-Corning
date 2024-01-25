        REPOSITORY NOTES
Add Spline Math Class
Use all utility classes in every possible spot
Wrist may need to use FeedbackController, test that
Lower the allowed Spline Error
Values are not clipped or scaled in subsystems, MUST be done in each teleop and both Robots

        TODOS:
ControllerConstants - tune deadband
ControllerConstants - tune wait time

HandConstants - tune Grabber ranges

ArmConstants - tune PlaneLauncher range
ArmConstants - tune shoulder max power
ArmConstants - tune climb max power
ArmConstants - tune shoulder p
ArmConstants - tune shoulder f
ArmConstants - tune arm vertical position
ArmConstants - tune wrist max power
ArmConstants - tune wrist p
ArmConstants - tune wrist fourbar positions
ArmConstants - tune fourbar switching angle

AprilTagVisionConstants - tune camera offsets

Drivetrain - Write it
DrivetrainConstants - Write it
TensorFlowVision - make it output Left, Right, or Center
TensorFlowVisionConstants - write it