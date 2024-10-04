package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class PowerLimiter {
    private double lastPower = 0;
    private ElapsedTime timer;
    public double accelerationLimit = 0; // KEEP IN MIND VELOCITY IS POWER
    public PowerLimiter(double accelerationLimit) {
        this.accelerationLimit = accelerationLimit;
        this.timer = new ElapsedTime();
        timer.reset();
    }
//    public double limit(double power) {
//        double dt = timer.now(TimeUnit.SECONDS);
//        double velocity = power - lastPower;
//        double acceleration = velocity - lastVelocity;
//        velocity /= dt;
//        acceleration /= dt;
//        if (acceleration > accelerationLimit) {
//            velocity = lastVelocity + accelerationLimit;
//            power = lastPower + velocity;
//        } else if (acceleration < -accelerationLimit) {
//            velocity = lastVelocity - accelerationLimit;
//            power = lastPower + velocity;
//        }
//        lastPower = power;
//        lastVelocity = velocity;
//        return power;
//    }
    public double limit(double power) {
        double dt = timer.now(TimeUnit.SECONDS);
        double acceleration = power - lastPower;
        acceleration /= dt;
        if (acceleration > accelerationLimit) {
            power = lastPower + accelerationLimit;
        } else if (acceleration < -accelerationLimit) {
            power = lastPower - accelerationLimit;
        }
        lastPower = power;
        return power;
    }
    public void setAccelerationLimit(double accelerationLimit) {
        this.accelerationLimit = accelerationLimit;
    }
}
