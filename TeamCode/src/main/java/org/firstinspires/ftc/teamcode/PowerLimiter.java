package org.firstinspires.ftc.teamcode;

public class PowerLimiter {
    private double lastPower = 0;
    private double lastVelocity = 0;
    public double accelerationLimit = 0;
    public PowerLimiter(double accelerationLimit) {
        this.accelerationLimit = accelerationLimit;
    }
    public double limit(double power) {
        double velocity = power - lastPower;
        double acceleration = power - lastVelocity;
        if (acceleration > accelerationLimit) {
            velocity = lastVelocity + accelerationLimit;
            power = lastPower + velocity;
        } else if (acceleration < -accelerationLimit) {
            velocity = lastVelocity - accelerationLimit;
            power = lastPower + velocity;
        }
        lastPower = power;
        lastVelocity = velocity;
        return power;
    }
}
