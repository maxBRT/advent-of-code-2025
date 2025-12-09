package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.List;

import io.github.maxBRT.Utilities.Point;

public class DayNine extends Day {
    private List<Point> points = new ArrayList<>();
    private List<List<Point>> verticalEdges = new ArrayList<>();
    private List<List<Point>> horizontalEdges = new ArrayList<>();
    private long largestArea = 0;

    public DayNine() {
    }

    public String solvePartOne() {
        parseInput();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (isRectangle(points.get(i), points.get(j))) {
                    long area = getRectangleArea(points.get(i), points.get(j));
                    if (area > largestArea) {
                        largestArea = area;
                    }
                }
            }
        }
        return Long.toString(largestArea);
    }

    public String solvePartTwo() {
        largestArea = 0;
        points.sort((a, b) -> compareX(a, b));
        for (int i = 0; i < points.size() - 1; i++) {
            if (points.get(i).getX() == points.get(i + 1).getX()) {
                verticalEdges.add(List.of(points.get(i), points.get(i + 1)));
            }
        }
        points.sort((a, b) -> compareY(a, b));
        for (int i = 0; i < points.size() - 1; i++) {
            if (points.get(i).getY() == points.get(i + 1).getY()) {
                horizontalEdges.add(List.of(points.get(i), points.get(i + 1)));
            }
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (isRectangle(points.get(i), points.get(j))) {
                    if (!edgeInPolygon(points.get(i), points.get(j), horizontalEdges, verticalEdges)) {
                        long area = getRectangleArea(points.get(i), points.get(j));
                        if (area > largestArea) {
                            largestArea = area;
                        }
                    }
                }
            }
        }

        return Long.toString(largestArea);
    }

    private boolean edgeInPolygon(Point p1, Point p2, List<List<Point>> horizontalEdges,
            List<List<Point>> verticalEdges) {
        int minX = Math.min(p1.getX(), p2.getX());
        int maxX = Math.max(p1.getX(), p2.getX());
        int minY = Math.min(p1.getY(), p2.getY());
        int maxY = Math.max(p1.getY(), p2.getY());

        for (List<Point> edge : verticalEdges) {
            int wallX = edge.get(0).getX();
            int wallYMin = edge.get(0).getY();
            int wallYMax = edge.get(1).getY();

            if (wallX > minX && wallX < maxX) {
                if (wallYMin < minY && wallYMax > maxY) {
                    return true;
                }
            }
        }
        for (List<Point> edge : horizontalEdges) {
            int wallY = edge.get(0).getY();
            int wallXMin = Math.min(edge.get(0).getX(), edge.get(1).getX());
            int wallXMax = Math.max(edge.get(0).getX(), edge.get(1).getX());

            if (wallY > minY && wallY < maxY) {
                if (Math.max(minX, wallXMin) < Math.min(maxX, wallXMax)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void parseInput() {
        for (String line : this.getInput().split("\n")) {
            String[] pos = line.split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            Point point = new Point(x, y);
            this.points.add(point);
        }
    }

    private boolean isRectangle(Point a, Point b) {
        return (a.getX() != b.getX() && a.getY() != b.getY());
    }

    private long getRectangleArea(Point a, Point b) {
        long width = Math.abs(b.getX() - a.getX()) + 1;
        long height = Math.abs(b.getY() - a.getY()) + 1;
        return (long) width * height;
    }

    private int compareX(Point a, Point b) {
        int x = Integer.compare(a.getX(), b.getX());
        if (x == 0) {
            return Integer.compare(a.getY(), b.getY());
        }
        return x;
    }

    private int compareY(Point a, Point b) {
        int y = Integer.compare(a.getY(), b.getY());
        if (y == 0) {
            return Integer.compare(a.getX(), b.getX());
        }
        return y;
    }
}
