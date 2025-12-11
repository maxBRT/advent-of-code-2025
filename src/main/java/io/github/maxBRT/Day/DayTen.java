package io.github.maxBRT.Day;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTen extends Day {

    static class Machine {
        int[] target;
        List<int[]> buttons;

        public Machine(int[] target, List<int[]> buttons) {
            this.target = target;
            this.buttons = buttons;
        }
    }

    public DayTen() {
    }

    public String solvePartOne() {
        int totalPresses = 0;
        List<Machine> machines = parse();
        for (Machine m : machines) {
            totalPresses += solveBruteForce(m);
        }
        return Integer.toString(totalPresses);
    }

    public int solveBruteForce(Machine machine) {
        int numButtons = machine.buttons.size();
        int modelLen = machine.target.length;
        int minPresses = Integer.MAX_VALUE;

        int limit = 1 << numButtons;

        for (int i = 0; i < limit; i++) {

            int[] currentState = new int[modelLen];
            int currentPressCount = 0;

            for (int btnIdx = 0; btnIdx < numButtons; btnIdx++) {

                // Use bitwise logic to check if the bit at 'btnIdx' is 1
                // If i = 5 (binary 101), then buttons 0 and 2 are pressed.
                if (((i >> btnIdx) & 1) == 1) {
                    currentPressCount++;

                    // Apply this button's effect (XOR) to the current state
                    int[] btnVector = machine.buttons.get(btnIdx);
                    for (int light = 0; light < modelLen; light++) {
                        currentState[light] ^= btnVector[light];
                    }
                }
            }

            // 4. Check if we matched the target
            if (java.util.Arrays.equals(currentState, machine.target)) {
                // We found a valid solution! Is it the smallest one?
                if (currentPressCount < minPresses) {
                    minPresses = currentPressCount;
                }
            }
        }

        return (minPresses == Integer.MAX_VALUE) ? -1 : minPresses;
    }

    public List<Machine> parse() {
        String content = getInput();
        List<Machine> machines = new ArrayList<>();

        // Split the whole file string into lines
        String[] lines = content.split("\\r?\\n");
        Pattern buttonPattern = Pattern.compile("\\(([^)]+)\\)");

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty())
                continue;

            String[] parts = line.split("\\s+");
            String modelStr = parts[0];

            // --- Parse Target ---
            // Remove brackets [ ]
            String cleanModel = modelStr.substring(1, modelStr.length() - 1);
            int modelLen = cleanModel.length();
            int[] target = new int[modelLen];

            for (int i = 0; i < modelLen; i++) {
                target[i] = (cleanModel.charAt(i) == '#') ? 1 : 0;
            }

            // --- Parse Buttons ---
            List<int[]> buttons = new ArrayList<>();
            Matcher m = buttonPattern.matcher(line);

            while (m.find()) {
                String contentInner = m.group(1); // "0,2,3"
                String[] indices = contentInner.split(",");

                // Create a vector of Zeros
                int[] buttonVec = new int[modelLen];

                for (String indexStr : indices) {
                    if (!indexStr.trim().isEmpty()) {
                        int lightIndex = Integer.parseInt(indexStr.trim());
                        if (lightIndex < modelLen) {
                            buttonVec[lightIndex] = 1;
                        }
                    }
                }
                buttons.add(buttonVec);
            }
            machines.add(new Machine(target, buttons));
        }
        return machines;
    }

}
