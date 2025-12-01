package io.github.maxBRT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import io.github.maxBRT.Day.Day;
import io.github.maxBRT.Day.DayOne;
import io.github.maxBRT.Day.DayTwo;
import io.github.maxBRT.Day.DayThree;
import io.github.maxBRT.Day.DayFour;
import io.github.maxBRT.Day.DayFive;
import io.github.maxBRT.Day.DaySix;
import io.github.maxBRT.Day.DaySeven;
import io.github.maxBRT.Day.DayEight;
import io.github.maxBRT.Day.DayNine;
import io.github.maxBRT.Day.DayTen;
import io.github.maxBRT.Day.DayEleven;
import io.github.maxBRT.Day.DayTwelve;

public class App {
    public static void main(String[] args) {
        String day = args[0];
        String inputPath = "src/main/java/io/github/maxBRT/Inputs/day" + day + ".txt";

        Map<String, Supplier<Day>> days = new HashMap<>();
        days.put("1", DayOne::new);
        days.put("2", DayTwo::new);
        days.put("3", DayThree::new);
        days.put("4", DayFour::new);
        days.put("5", DayFive::new);
        days.put("6", DaySix::new);
        days.put("7", DaySeven::new);
        days.put("8", DayEight::new);
        days.put("9", DayNine::new);
        days.put("10", DayTen::new);
        days.put("11", DayEleven::new);
        days.put("12", DayTwelve::new);

        Supplier<Day> daySupplier = days.get(day);
        if (daySupplier == null) {
            System.err.println("Day " + day + " not found!");
            return;
        }

        try {
            Day today = daySupplier.get();
            today.setInput(inputPath);

            System.out.println("Part One: " + today.solvePartOne());
            System.out.println("Part Two: " + today.solvePartTwo());

        } catch (IOException e) {
            System.err.println("Error reading input file!");
        }
    }

}