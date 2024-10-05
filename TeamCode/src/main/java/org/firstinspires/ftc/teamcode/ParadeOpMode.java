package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class ParadeOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        PowerLimiter leftLimiter = new PowerLimiter(0.005);
        PowerLimiter rightLimiter = new PowerLimiter(0.005);
        PowerLimiter armLimiter = new PowerLimiter(0.01);

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        boolean slowMode = false;
        boolean cruiseCntrl = false;
        boolean armAuto = false;
        ElapsedTime armTimer = new ElapsedTime();
        double power = 0;
        waitForStart();

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);



            if (currentGamepad1.circle  && !previousGamepad1.circle) {
                slowMode = !slowMode;
            }
//
            if (currentGamepad1.cross && !previousGamepad1.cross) {
                cruiseCntrl = !cruiseCntrl;
            }
            if (currentGamepad1.triangle && !previousGamepad1.triangle) {
                armTimer.reset();
                armAuto = !armAuto;
            }

            if (!cruiseCntrl){
                power = gamepad1.left_stick_y;;
            }

            double armPower;
            if (armAuto) {
                double s = armTimer.seconds();
                telemetry.addData("s", s);
                if (s > 8) {
                    armTimer.reset();
                    armPower = 0;
                } else if (s > 6) {
                    armPower = 0;
                } else if (s > 4) {
                    armPower = -0.3;
                } else if (s > 2) {
                    armPower = 0;
                } else {
                    armPower = 0.3;
                }
            } else {
                armPower = gamepad2.right_stick_y / 2;
            }
            arm.setPower(armLimiter.limit(armPower));

            if (currentGamepad1.triangle && !previousGamepad1.triangle) {
//                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            double turn = (gamepad1.right_trigger - gamepad1.left_trigger); // positive = right, negative = left

            if (slowMode){
                if (!cruiseCntrl) {
                    power = power / 2;
                }
                turn = turn / 1.5;
            } else {
//                turn = turn;
            }


            double leftPower = leftLimiter.limit(power + turn);
            fl.setPower(leftPower);
            bl.setPower(leftPower);

            double rightPower = rightLimiter.limit(-power + turn);
            fr.setPower(rightPower);
            br.setPower(rightPower);

            telemetry.addData("Power",power);
            telemetry.addData("Cruise cntrl",cruiseCntrl);
            telemetry.addData("Slow mode",slowMode);
            telemetry.addData("arm", armPower);
            telemetry.addData("arm pos",arm.getCurrentPosition());
            telemetry.update();
        }
    }
}
