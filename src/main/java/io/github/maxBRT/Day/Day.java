package io.github.maxBRT.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Day {
    protected String input;

    public String solvePartOne() {
        return "not implemented yet";
    };

    public String solvePartTwo() {
        return "not implemented yet";
    };

    public void setInput(String inputPath) throws IOException {
        this.input = Files.readString(Paths.get(inputPath));
    }

    public String getInput() {
        return input;
    }
}
