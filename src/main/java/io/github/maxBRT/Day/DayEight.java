package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.maxBRT.Utilities.Edge;
import io.github.maxBRT.Utilities.JunctionBox;

public class DayEight extends Day {

    private List<JunctionBox> boxes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private int[] parents;
    private long[] sizes;

    public DayEight() {
    }

    public String solvePartOne() {
        parseInput();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                JunctionBox a = boxes.get(i);
                JunctionBox b = boxes.get(j);
                Edge edge = new Edge(i, j, a.getDistance(b));
                edges.add(edge);
            }
        }

        this.edges.sort((a, b) -> Long.compare(a.weight(), b.weight()));
        this.parents = new int[boxes.size()];
        this.sizes = new long[boxes.size()];
        Arrays.fill(sizes, 1);
        for (int i = 0; i < boxes.size(); i++) {
            this.parents[i] = i;
        }
        int count = 0;
        for (int i = 0; count < 1000; i++) {
            Edge edge = edges.get(i);
            union(edge.from(), edge.to());
            count++;
        }

        List<Long> finalSizes = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            if (find(i) == i) {
                finalSizes.add(sizes[i]);
            }
        }

        finalSizes.sort((a, b) -> Long.compare(b, a));
        long result = (long) finalSizes.get(0) * finalSizes.get(1) * finalSizes.get(2);
        return Long.toString(result);
    }

    private void parseInput() {
        for (String line : this.getInput().split("\n")) {
            String[] pos = line.split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            int z = Integer.parseInt(pos[2]);
            JunctionBox box = new JunctionBox(x, y, z);
            this.boxes.add(box);
        }
    }

    public String solvePartTwo() {
        parseInput();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                JunctionBox a = boxes.get(i);
                JunctionBox b = boxes.get(j);
                Edge edge = new Edge(i, j, a.getDistance(b));
                edges.add(edge);
            }
        }

        this.edges.sort((a, b) -> Long.compare(a.weight(), b.weight()));

        this.parents = new int[boxes.size()];
        this.sizes = new long[boxes.size()];
        Arrays.fill(sizes, 1);
        for (int i = 0; i < boxes.size(); i++) {
            this.parents[i] = i;
        }

        Edge lastEdge = null;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (union(edge.from(), edge.to())) {
                lastEdge = edge;
            }
        }

        if (lastEdge == null) {
            return "Error: No edges were connected!";
        }

        int x1 = boxes.get(lastEdge.from()).getX();
        int x2 = boxes.get(lastEdge.to()).getX();
        return Long.toString((long) x1 * x2);
    }

    private int find(int a) {
        if (parents[a] != a) {
            parents[a] = find(parents[a]); // Path compression
        }
        return parents[a];
    }

    private boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if (x != y) {
            if (sizes[x] < sizes[y]) {
                parents[x] = y;
                sizes[y] += sizes[x];
            } else {
                parents[y] = x;
                sizes[x] += sizes[y];
            }
            return true;
        }
        return false;
    }
}
