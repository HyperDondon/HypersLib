package com.hyperdondon.lib.util.geometry;

import org.bukkit.util.Vector;

public class SphericalVector extends Vector {
    public SphericalVector(double r, double yaw, double pitch) {
        this.x = r * Math.cos(pitch) * Math.cos(yaw);
        this.y = r * Math.sin(pitch);
        this.z = r * Math.cos(pitch) * Math.sin(yaw);
    }

    public SphericalVector(float r, float yaw, float pitch) {
        SphericalVector vector = new SphericalVector((double) r, yaw, pitch);
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public SphericalVector(float r, float yaw) {
        SphericalVector vector = new SphericalVector((double) r, yaw, 0);
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public SphericalVector(double r, double yaw) {
        SphericalVector vector = new SphericalVector(r, yaw, 0);
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public static double getParticleAmount(double r, double density) {
        return (2 * Math.PI * r / density);
    }

    public static double getYaw(double r, double iteration, double density) {
        return (iteration * 360) / getParticleAmount(r, density);
    }
}
