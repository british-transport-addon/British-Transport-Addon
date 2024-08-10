package org.eu.awesomekalin.jta.mod.entity.vehicle;

public class Seat {
    private final double x, y, z; // Relative position to vehicle's center

    public Seat(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
