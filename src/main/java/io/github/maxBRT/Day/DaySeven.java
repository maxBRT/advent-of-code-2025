package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DaySeven extends Day {
    public Set<List<Integer>> visited = new HashSet<>();
    private Map<List<Integer>, Boolean> memo = new HashMap<>();
    private Map<List<Integer>, Long> pathCount = new HashMap<>();

    public DaySeven() {
    }

    public String solvePartOne() {
        List<List<Character>> input = parseInput();
        visit(input, 0, input.get(0).indexOf('S'));
        return Integer.toString(visited.size());
    }

    public String solvePartTwo() {
        List<List<Character>> input = parseInput();
        long count = countPaths(input, 0, input.get(0).indexOf('S'));
        return Long.toString(count);
    }

    private void visit(List<List<Character>> input, int row, int col) {
        if (row < 0 || row >= input.size() || col < 0 || col >= input.get(row).size()) {
            return;
        }

        List<Integer> key = Arrays.asList(row, col);

        if (memo.containsKey(key)) {
            return;
        }
        memo.put(key, true);

        char current = input.get(row).get(col);

        if (current == '.' || current == 'S') {
            visit(input, row + 1, col);
        } else if (current == '^') {
            visited.add(key);
            visit(input, row + 1, col - 1);
            visit(input, row + 1, col + 1);
        }
    }

    private Long countPaths(List<List<Character>> input, int row, int col) {
        if (row < 0 || row >= input.size() || col < 0 || col >= input.get(row).size()) {
            return 0L;
        }
        if (row == input.size() - 1) {
            return 1L;
        }
        List<Integer> key = Arrays.asList(row, col);
        if (pathCount.containsKey(key)) {
            return pathCount.get(key);
        }
        char current = input.get(row).get(col);
        long count = 0;

        if (current == '.' || current == 'S') {
            count = countPaths(input, row + 1, col);
        } else if (current == '^') {
            count = countPaths(input, row + 1, col - 1) + countPaths(input, row + 1, col + 1);
        }

        pathCount.put(key, count);
        return count;

    }

    private List<List<Character>> parseInput() {
        List<List<Character>> input = new ArrayList<>();
        for (String line : this.getInput().split("\n")) {
            List<Character> lineInput = new ArrayList<>();
            for (Character c : line.toCharArray()) {
                lineInput.add(c);
            }
            input.add(lineInput);
        }
        return input;
    }

}
