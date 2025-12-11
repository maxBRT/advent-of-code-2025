package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class DayEleven extends Day {

    private Map<String, List<String>> paths = new HashMap<>();

    private Map<String, Long> memo = new HashMap<>();

    public DayEleven() {
    }

    public String solvePartOne() {
        parse();

        long totalPaths = 0L;

        memo.clear();

        totalPaths += countPathsRecursive("you");

        return Long.toString(totalPaths);
    }

    public String solvePartTwo() {
        long totalPaths = 0L;

        memo.clear();

        totalPaths += countPathsRecursiveWithCheck("svr", false, false);

        return Long.toString(totalPaths);
    }

    private long countPathsRecursive(String current) {
        if (current.equals("out")) {
            return 1;
        }

        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        long total = 0;
        for (String neighbor : paths.getOrDefault(current, Collections.emptyList())) {
            total += countPathsRecursive(neighbor);
        }

        memo.put(current, total);

        return total;
    }

    private long countPathsRecursiveWithCheck(String current, boolean hasFft, boolean hasDac) {
        if (current.equals("fft")) {
            hasFft = true;
        }
        if (current.equals("dac")) {
            hasDac = true;
        }

        if (current.equals("out")) {
            return hasDac && hasFft ? 1 : 0;
        }

        String cacheKey = current + ":" + hasFft + ":" + hasDac;
        if (memo.containsKey(cacheKey)) {
            return memo.get(cacheKey);
        }

        long total = 0;
        for (String neighbor : paths.getOrDefault(current, Collections.emptyList())) {
            total += countPathsRecursiveWithCheck(neighbor, hasFft, hasDac);
        }

        memo.put(cacheKey, total);

        return total;
    }

    private void parse() {
        // Split by lines
        List<String> lines = new ArrayList<>();
        for (String line : getInput().split("\n")) {
            lines.add(line);
        }

        // Split by :
        for (String line : lines) {
            List<String> parts = Arrays.asList(line.split(":"));
            String node = parts.get(0).strip();
            String neighbors = parts.get(1).strip();

            // Split by spaces
            this.paths.put(node, Arrays.asList(neighbors.split(" ")));
        }
    }

}
