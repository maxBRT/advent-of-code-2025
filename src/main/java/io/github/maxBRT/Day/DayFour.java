package io.github.maxBRT.Day;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class Point {
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
}

public class DayFour extends Day {

    public int total = 0;
    public Set<Point> toRemove = new HashSet<>();
    public char[][] grid;

    public DayFour() {
    }

    public String solvePartOne() {
        this.grid = filterInput();
        breadthFirstSearch(grid, 0, 0);
        return Integer.toString(this.total);
    }

    public String solvePartTwo() {
        this.total = 0;
        this.grid = filterInput();
        breadthFirstSearch(this.grid, 0, 0);
        while (this.toRemove.size() != 0) {
            breadthFirstRemove(this.grid, 0, 0);
            breadthFirstSearch(this.grid, 0, 0);
        }
        return Integer.toString(this.total);
    }

    private char[][] filterInput() {
        String[] lines = getInput().split("\n");
        char[][] graph = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            graph[i] = lines[i].toCharArray();
        }
        return graph;
    }

    private void breadthFirstSearch(char[][] grid, int x, int y) {
        Point start = new Point(x, y);
        start.setValue(grid[x][y]);
        Queue<Point> queue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        int neighboringRolls = 0;

        while (queue.size() > 0) {
            Point current = queue.poll();
            neighboringRolls = 0;

            for (Point p : current.neighborgs(grid)) {
                if (p.getValue() == '@') {
                    neighboringRolls++;
                }

                if (!visited.contains(p)) {
                    visited.add(p);
                    queue.add(p);
                }
            }

            if (current.getValue() == '@' && neighboringRolls < 4) {
                this.toRemove.add(current);
                this.total++;
            }
        }

    }

    private void breadthFirstRemove(char[][] grid, int x, int y) {
        Point start = new Point(x, y);
        start.setValue(grid[y][x]);
        Queue<Point> queue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (queue.size() > 0) {
            Point current = queue.poll();
            for (Point p : current.neighborgs(grid)) {
                if (toRemove.contains(p)) {
                    toRemove.remove(p);
                    this.grid[p.getY()][p.getX()] = '.';
                }
                if (!visited.contains(p)) {
                    visited.add(p);
                    queue.add(p);
                }
            }
        }

    }
}
