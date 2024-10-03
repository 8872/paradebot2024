package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ParadeOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");

        waitForStart();

        while (opModeIsActive()) {
//            double leftPower = -gamepad1.left_stick_y;
//            fl.setPower(leftPower);
//            bl.setPower(leftPower);
//
//            double rightPower = gamepad1.right_stick_y;
//            fr.setPower(rightPower);
//            br.setPower(rightPower);


            double power = -gamepad1.left_stick_y;
            double turn = gamepad1.right_trigger - gamepad1.left_trigger; // positive = right, negative = left

            double leftPower = power + turn;
            fl.setPower(leftPower);
            bl.setPower(leftPower);

            double rightPower = -power + turn;
            fr.setPower(rightPower);
            br.setPower(rightPower);
        }
    }
}
