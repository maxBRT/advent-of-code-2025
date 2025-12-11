package io.github.maxBRT.Utilities;

public class JunctionBox {
    final int x;
    final int y;
    final int z;

    public JunctionBox(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public long getDistance(JunctionBox other) {
        // 1. Cast to long IMMEDIATELY when calculating delta
        long dx = (long) this.x - other.x;
        long dy = (long) this.y - other.y;
        long dz = (long) this.z - other.z;

        // 2. Now long * long = long (No overflow)
        return (dx * dx) + (dy * dy) + (dz * dz);
    }
}
