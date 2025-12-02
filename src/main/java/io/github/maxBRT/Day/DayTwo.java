package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayTwo extends Day {

    private Long total = 0L;

    public DayTwo() {
    }

    public String solvePartOne() {
        // Separate the ranges by comma
        String[] ranges = input.split(",");
        for (String rangeString : ranges) {
            // Get the end of the range and the start of the range
            String[] range = rangeString.split("-");
            String current = range[0];
            String end = range[1];

            do {
                int mid = current.length() / 2;
                String[] halves = { current.substring(0, mid), current.substring(mid) };
                if (!halves[0].equals(halves[1])) {
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                total += Long.parseLong(current);
                current = Long.toString(Long.parseLong(current) + 1);

            } while (Long.parseLong(current) <= Long.parseLong(end));

            // Split the numbers in half and check if it matched the other half
        }
        return total.toString();
    }

    public String solvePartTwo() {
        this.total = 0L;

        // Separate the ranges by comma
        String[] ranges = input.split(",");
        for (String rangeString : ranges) {
            // Get the end of the range and the start of the range
            String[] range = rangeString.split("-");
            String current = range[0];
            String end = range[1];

            do {
                // Check if the halves match
                int mid = current.length() / 2;
                String[] halves = { current.substring(0, mid), current.substring(mid) };
                if (halves[0].equals(halves[1])) {
                    total += Long.parseLong(current);
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                // Create a set with the numbers
                Set<Character> nSet = new HashSet<>();
                for (char c : current.toCharArray()) {
                    nSet.add(c);
                }

                // Case 1 unique char and len > 1 add
                if (nSet.size() == 1 && current.length() > 1) {
                    total += Long.parseLong(current);
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                if (current.length() % nSet.size() != 0) {
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                if (nSet.size() == current.length()) {
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                // Split the string in equal parts
                int partLength = nSet.size();
                int nParts = current.length() / partLength;
                List<String> parts = new ArrayList<>();
                int startIndex = 0;

                for (int i = 0; i < nParts; i++) {
                    int endIndex = startIndex + partLength;
                    parts.add(current.substring(startIndex, endIndex));
                    startIndex = endIndex;
                }

                // Compare all the parts with the first one
                boolean match = false;
                for (String p : parts) {
                    if (!p.equals(parts.get(0))) {
                        match = true;
                        break;
                    }
                }
                if (match) {
                    current = Long.toString(Long.parseLong(current) + 1);
                    continue;
                }

                total += Long.parseLong(current);
                current = Long.toString(Long.parseLong(current) + 1);
            } while (Long.parseLong(current) <= Long.parseLong(end));
        }
        return total.toString();
    }
}
