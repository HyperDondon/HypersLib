package com.hyperdondon.lib.geometry.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for location manipulation. Usually for particles and other effects.
 */
public class GeometryUtil {
    //Circular Stuff
    public static Vector getSphericalVector(double pRadius, double pYaw, double pPitch) {
        double x = pRadius * Math.cos(pPitch) * Math.cos(pYaw);
        double y = pRadius * Math.sin(pPitch);
        double z = pRadius * Math.cos(pPitch) * Math.sin(pYaw);
        return new Vector(x, y, z);
    }

    public static Vector getSphericalVector(double pRadius, double pYaw) {
        return getSphericalVector(pRadius, pYaw, 0);
    }

    public static double getCircularParticleAmount(double pRadius, double density) {
        return (2 * Math.PI * pRadius / density);
    }

    public static double getCircularYaw(double pRadius, double iteration, double density) {
        return (iteration * 360) / getCircularParticleAmount(pRadius, density);
    }

    //Directional stuff
    public static Location forwardsBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        Vector direction = clone.getDirection().normalize();
        return clone.add(direction.multiply(pBlocks));
    }

    public static Location backwardsBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        Vector direction = clone.getDirection().normalize().multiply(-pBlocks);
        return clone.add(direction);
    }

    public static Location leftBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        float pYaw = clone.getYaw();

        double radians = Math.toRadians(pYaw - 90);
        Vector left = new Vector(-Math.sin(radians), 0, Math.cos(radians)).normalize().multiply(pBlocks);
        return clone.add(left);
    }

    public static Location rightBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        float pYaw = clone.getYaw();

        double radians = Math.toRadians(pYaw + 90);
        Vector right = new Vector(-Math.sin(radians), 0, Math.cos(radians)).normalize().multiply(pBlocks);
        return clone.add(right);
    }

    public static Location leftBlocksWithPitch(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        clone.setYaw(clone.getYaw() - 90);
        Vector direction = clone.getDirection().normalize().multiply(pBlocks);
        return clone.add(direction);
    }

    public static Location rightBlocksWithPitch(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        clone.setYaw(clone.getYaw() + 90);
        Vector direction = clone.getDirection().normalize().multiply(pBlocks);
        return clone.add(direction);
    }

    public static Location aboveBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        Vector direction = clone.getDirection(); // Forward
        Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize(); // Right
        Vector up = right.clone().crossProduct(direction).normalize(); // Local up
        return clone.add(up.multiply(pBlocks));
    }

    public static Location underBlocks(Location pLocation, double pBlocks) {
        Location clone = pLocation.clone();
        Vector direction = clone.getDirection(); // Forward
        Vector right = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize(); // Right
        Vector up = right.clone().crossProduct(direction).normalize(); // Local up
        return clone.subtract(up.multiply(pBlocks));
    }

    public static List<Block> getBlocksBetween(Location loc1, Location loc2) {
        List<Block> blocks = new ArrayList<>();

        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        World world = loc1.getWorld();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }

        return blocks;
    }
}