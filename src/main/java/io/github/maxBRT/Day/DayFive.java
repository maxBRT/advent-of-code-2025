package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public class DayFive extends Day {

    public Long total = 0L;

    public DayFive() {
    }

    public String solvePartOne() {
        List<String> lines = filterInput();
        boolean isRange = true;
        List<Long[]> validIds = new ArrayList<>();
        for (String line : lines) {
            if (line == "") {
                isRange = false;
                continue;
            }

            if (isRange) {
                validIds.add(splitRange(line));
                continue;
            }

            boolean isValid = false;
            for (Long[] range : validIds) {
                if (isValid) {
                    break;
                }
                isValid = Long.parseLong(line) >= range[0] && Long.parseLong(line) <= range[1];
            }
            this.total += isValid ? 1 : 0;
            isValid = false;
        }
        return Long.toString(this.total);
    }

    public String solvePartTwo() {
        this.total = 0L;
        List<String> lines = filterInput();
        List<Long[]> ranges = new ArrayList<>();
        for (String line : lines) {
            if (line == "") {
                break;
            }
            ranges.add(splitRange(line));
        }

        Long currentEnd = Long.MIN_VALUE;

        ranges.sort((a, b) -> Long.compare(a[0], b[0]));

        for (Long[] range : ranges) {
            Long start = range[0];
            Long end = range[1];

            if (start > currentEnd) {
                total += end - start + 1;
            } else if (end > currentEnd) {
                total += end - currentEnd;
            }
            currentEnd = Math.max(currentEnd, end);
        }

        return Long.toString(total);
    }

    private Long[] splitRange(String rangeString) {
        String[] range = rangeString.split("-");
        Long start = Long.parseLong(range[0]);
        Long end = Long.parseLong(range[1]);
        return new Long[] { start, end };
    }

    private List<String> filterInput() {
        List<String> lines = new ArrayList<>();
        for (String line : this.getInput().split("\n")) {
            lines.add(line);
        }
        return lines;
    }

}
