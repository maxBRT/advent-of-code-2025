package io.github.maxBRT.Day;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import io.github.maxBRT.Utilities.Point;

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
