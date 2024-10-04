package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class BackupParadeOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");

        PowerLimiter leftLimiter = new PowerLimiter(0.005);
        PowerLimiter rightLimiter = new PowerLimiter(0.005);

        waitForStart();

        while (opModeIsActive()) {
            double power = -gamepad1.left_stick_y;
            double turn = gamepad1.right_trigger - gamepad1.left_trigger; // positive = right, negative = left

            double leftPower = leftLimiter.limit(power + turn);
            fl.setPower(leftPower);
            bl.setPower(leftPower);

            double rightPower = rightLimiter.limit(-power + turn);
            fr.setPower(rightPower);
            br.setPower(rightPower);
        }
    }
}
