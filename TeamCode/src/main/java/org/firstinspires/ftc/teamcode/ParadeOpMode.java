package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class ParadeOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        boolean slowMode = false;
        boolean cruiseCntrl = false;
        double power = 0;

        waitForStart();

        while (opModeIsActive()) {
//            double leftPower = -gamepad1.left_stick_y;
//            fl.setPower(leftPower);
//            bl.setPower(leftPower);
//
//            double rightPower = gamepad1.right_stick_y;
//            fr.setPower(rightPower);
//            br.setPower(rightPower);
            previousGamepad1.copy(currentGamepad1);



            if (currentGamepad1.circle && !previousGamepad1.circle) {
                slowMode = !slowMode;
            }

            if (currentGamepad1.cross && !previousGamepad1.cross) {
                cruiseCntrl = !cruiseCntrl;
            }

            if (!cruiseCntrl){
                power = -gamepad1.left_stick_y;;
            }

            double turn = gamepad1.right_trigger - gamepad1.left_trigger; // positive = right, negative = left

            if (slowMode){
                power = power/2;
                turn = turn/3;
            } else {
                turn = turn/2;
            }


            double leftPower = power + turn;
            fl.setPower(leftPower);
            bl.setPower(leftPower);

            double rightPower = -power + turn;
            fr.setPower(rightPower);
            br.setPower(rightPower);

            currentGamepad1.copy(gamepad1);
        }
    }
}
