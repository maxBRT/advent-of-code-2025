package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.List;

public class DayThree extends Day {

    int firstDigit = 0;
    int firstIndex = 0;
    int secondDigit = 0;
    int secondIndex = 0;
    Long total = 0L;

    public DayThree() {
    }

    public String solvePartOne() {
        List<List<Integer>> batteries = this.filterInput();
        for (List<Integer> battery : batteries) {
            for (int i = 0; i < battery.size(); i++) {
                if (battery.get(i) > firstDigit) {
                    firstDigit = battery.get(i);
                    firstIndex = i;
                }
            }
            for (int i = firstIndex < battery.size() - 1 ? firstIndex : 0; i < battery.size(); i++) {
                if (battery.get(i) > secondDigit && i != firstIndex) {
                    secondDigit = battery.get(i);
                    secondIndex = i;
                }
            }

            String n = firstIndex > secondIndex ? secondDigit + "" + firstDigit : firstDigit + "" + secondDigit;
            total += Long.parseLong(n);
            resetDigits();
        }
        return total.toString();
    }

    public String solvePartTwo() {
        total = 0L;
        List<List<Integer>> batteries = this.filterInput();
        for (List<Integer> battery : batteries) {
            resetDigits();
            int pass = 12;
            String res = "";
            while (pass > 0) {
                int maxIndex = battery.size() - pass;
                for (int i = this.firstIndex; i <= maxIndex; i++) {
                    if (battery.get(i) > this.firstDigit) {
                        this.firstIndex = i + 1;
                        this.firstDigit = battery.get(i);
                    }
                }
                res += this.firstDigit;
                this.firstDigit = 0;
                pass--;
            }
            total += Long.parseLong(res);
        }

        return total.toString();

    }

    private void resetDigits() {
        this.firstDigit = 0;
        this.firstIndex = 0;
        this.secondDigit = 0;
        this.secondIndex = 0;
    }

    private List<List<Integer>> filterInput() {
        // Split then input in an array of nums
        String[] lines = this.getInput().split("\n");
        // Loop to find the
        List<List<Integer>> batteries = new ArrayList<>();
        for (String line : lines) {
            List<Integer> list = new ArrayList<>();
            for (char digit : line.toCharArray()) {
                list.add(digit - '0');
            }
            batteries.add(list);
        }
        return batteries;
    }

}