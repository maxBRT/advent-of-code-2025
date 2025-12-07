package io.github.maxBRT.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySix extends Day {

    public DaySix() {
    }

    public String solvePartOne() {
        long total = 0L;
        List<List<String>> lists = parseInput();
        for (int i = 0; i < lists.get(0).size(); i++) {
            long n1 = Long.parseLong(lists.get(0).get(i));
            long n2 = Long.parseLong(lists.get(1).get(i));
            long n3 = Long.parseLong(lists.get(2).get(i));
            long n4 = Long.parseLong(lists.get(3).get(i));
            String sign = lists.get(4).get(i);
            if (sign.equals("+")) {
                long sum = ((n1 + n2) + n3) + n4;
                total += sum;
                continue;
            }
            if (sign.equals("*")) {
                long mult = ((n1 * n2) * n3) * n4;
                total += mult;
                continue;
            }
        }
        return Long.toString(total);
    }

    public String solvePartTwo() {
        long total = 0L;
        List<List<Character>> lists = parseInput2();
        for (List<Character> list : lists) {
            System.out.println(list.size());
        }
        List<Long> timeToRockList = new ArrayList<>();
        for (int i = lists.get(0).size() - 1; i >= 0; i--) {
            List<Character> timeToRockBuffer = new ArrayList<>();
            char c0 = lists.get(0).get(i);
            char c1 = lists.get(1).get(i);
            char c2 = lists.get(2).get(i);
            char c3 = lists.get(3).get(i);
            if (c0 != ' ')
                timeToRockBuffer.add(c0);
            if (c1 != ' ')
                timeToRockBuffer.add(c1);
            if (c2 != ' ')
                timeToRockBuffer.add(c2);
            if (c3 != ' ')
                timeToRockBuffer.add(c3);

            if (timeToRockBuffer.isEmpty()) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (Character c : timeToRockBuffer) {
                sb.append(c);
            }
            long number = Long.parseLong(sb.toString());

            char operator = lists.get(4).get(i);

            if (operator == '+') {
                timeToRockList.add(number);
                long sum = 0L;
                for (long num : timeToRockList) {
                    sum += num;
                }
                total += sum;
                timeToRockList.clear();
            } else if (operator == '*') {
                timeToRockList.add(number);
                long mult = 1L;
                for (long num : timeToRockList) {
                    mult *= num;
                }
                total += mult;
                timeToRockList.clear();
            } else {
                timeToRockList.add(number);
            }
        }
        return Long.toString(total);

    }

    public List<List<Character>> parseInput2() {
        List<List<Character>> lists = new ArrayList<>();

        String input = this.getInput();
        for (String line : input.split("\n")) {
            List<Character> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(c);
            }
            lists.add(row);
        }
        return lists;
    }

    public List<List<String>> parseInput() {
        List<List<String>> lists = new ArrayList<>();

        String input = this.getInput();
        for (String line : input.split("\n")) {
            List<String> row = new ArrayList<>(Arrays.asList(line.trim().split("\\s+")));
            lists.add(row);
        }

        for (int i = 0; i < lists.size(); i++) {
            List<String> currentRow = lists.get(i);

            for (int j = 0; j < currentRow.size(); j++) {
                String item = currentRow.get(j);

                if (item.equals("+") || item.equals("*")) {
                    continue;
                }

                try {
                    String formatted = String.format("%04d", Integer.parseInt(item));
                    currentRow.set(j, formatted);
                } catch (NumberFormatException e) {
                }
            }
        }
        return lists;
    }

}
