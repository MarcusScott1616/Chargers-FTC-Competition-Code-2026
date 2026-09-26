package org.firstinspires.ftc.teamcode.turretCode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
// note to self there are still errors in here
public class TurretAutoAlignOpMode extends OpMode {

    private Limelight3A Limelight;
    private TurretTurningMechanism turret = new TurretTurningMechanism();

    double[] stepSizes = {0.1, 0.01, 0.001, 0.0001, 0.00001};

    @Override
    public void init(){
        Limelight.init(hardwareMap,Telemetry);
        turret.init(hardwareMap);

        telemetry.addLine("Initialized all Mechanisms");
    }
    @Override
    public void start(){
        turret.resetTimer();

    }

    @Override
    public void loop(){
        //vision Logic

        // update code on the fly
        if (gamepad1.bWasPressed()){
            stepIndex= (stepIndex+1) %stepSizes.length;
        }
        aprilTagWebcam.update();
        AprilTagDetection id20 = aprilTagWebcam.getTagBySpecificID(20);

        turret.update(20);
        // change ID and "AprilTagDetection"
        if(id20 != null ){
            telemetry.addData("Cur ID", aprilTagWebcam);
        } else
        {
            telemetry.addLine("No aprilTag Detected, stopping turret Motor");
        }




    }



}
