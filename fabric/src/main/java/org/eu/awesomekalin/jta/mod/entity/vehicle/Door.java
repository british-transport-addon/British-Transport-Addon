package org.eu.awesomekalin.jta.mod.entity.vehicle;

public class Door {
    private final double x, y, z; // Relative position to vehicle's center
    private final double width, height; // Size of the door

    public Door(double x, double y, double z, double width, double height) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}