package io.github.maxBRT.Day;

public class DayOne extends Day {
    public int current;
    public int zeroCount;

    public DayOne() {

    }

    public String solvePartOne() {
        this.zeroCount = 0;
        this.current = 50;
        // Split the input into lines
        String[] lines = this.getInput().split("\n");
        // Loop through the list
        for (String line : lines) {
            // Set the sign based on the direction
            int sign = line.charAt(0) == 'L' ? -1 : 1;
            // Get the distance
            int distance = Integer.parseInt(line.substring(1));
            // Increment/Decrement the current position
            do {
                current += 1 * sign;
                distance -= 1;
                if (current == 100 && sign == 1) {
                    current = 0;
                }
                if (current == -1 && sign == -1) {
                    current = 99;
                }
            } while (distance != 0);

            // Increment the zero count if the current position is 0
            if (current == 0) {
                zeroCount++;
            }
        }
        return Integer.toString(zeroCount);
    }

    public String solvePartTwo() {
        this.zeroCount = 0;
        this.current = 50;

        // Split the input into lines
        String[] lines = this.getInput().split("\n");
        // Loop through the list
        for (String line : lines) {
            // Set the sign based on the direction
            int sign = line.charAt(0) == 'L' ? -1 : 1;
            // Get the distance
            int distance = Integer.parseInt(line.substring(1));
            // Increment/Decrement the current position
            do {
                current += 1 * sign;
                distance -= 1;
                if (current == 100 && sign == 1) {
                    current = 0;
                }
                if (current == -1 && sign == -1) {
                    current = 99;
                }
                // Increment the zero count if the current position is 0
                if (current == 0) {
                    zeroCount++;
                }
            } while (distance != 0);

        }
        return Integer.toString(zeroCount);
    }
}
