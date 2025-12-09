package io.github.maxBRT.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point implements Comparable {
    private final int x;
    private final int y;
    private char value;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char c) {
        this.value = c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    public String toString() {
        return this.value + " at: (x: " + this.x + ", y: " + this.y + " )";
    }

    public List<Point> neighborgs(char[][] grid) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(getX() - 1, getY()));
        points.add(new Point(getX() + 1, getY()));
        points.add(new Point(getX(), getY() - 1));
        points.add(new Point(getX(), getY() + 1));
        points.add(new Point(getX() - 1, getY() - 1));
        points.add(new Point(getX() - 1, getY() + 1));
        points.add(new Point(getX() + 1, getY() - 1));
        points.add(new Point(getX() + 1, getY() + 1));

        for (int i = points.size() - 1; i >= 0; i--) {
            if (!points.get(i).isValidPoint(grid[0].length, grid.length)) {
                points.remove(i);
            }
        }

        for (Point p : points) {
            p.setValue(grid[p.getY()][p.getX()]);
        }

        return points;
    }

    private boolean isValidPoint(int maxLen, int maxHeight) {
        boolean len = (this.getX() >= 0 && this.getX() < maxLen);
        boolean height = (this.getY() >= 0 && this.getY() < maxHeight);
        return len && height;
    }

    @Override
    public int compareTo(Object o) {
        Point other = (Point) o;

        long thisDistSq = (long) this.x * this.x + (long) this.y * this.y;
        long otherDistSq = (long) other.x * other.x + (long) other.y * other.y;

        return Long.compare(thisDistSq, otherDistSq);
    }
}
