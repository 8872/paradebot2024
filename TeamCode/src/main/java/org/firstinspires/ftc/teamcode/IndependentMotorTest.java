package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class IndependentMotorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("fl: left");
            telemetry.addLine("fr: right");
            telemetry.addLine("bl: up");
            telemetry.addLine("br: down");
            telemetry.update();

            if (gamepad1.dpad_left) {
                fl.setPower(0.5);
            } else {
                fl.setPower(0);
            }
            if (gamepad1.dpad_right) {
                fr.setPower(0.5);
            } else {
                fr.setPower(0);
            }
            if (gamepad1.dpad_up) {
                bl.setPower(0.5);
            } else {
                bl.setPower(0);
            }
            if (gamepad1.dpad_down) {
                br.setPower(0.5);
            } else {
                br.setPower(0);
            }
        }
    }
}
