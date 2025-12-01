# Advent of Code 2025

This is a Java-based Advent of Code 2025 solution framework.

Pull the fresh-start branch to get started!

### Project Structure

- `src/main/java/io/github/maxBRT/Day/` - Contains day classes (DayOne through DayTwelve)
- `src/main/java/io/github/maxBRT/Inputs/` - Contains input files (day1.txt through day12.txt)
- `src/main/java/io/github/maxBRT/App.java` - Main application to run solutions

### Running Solutions

To run a specific day's solution:

```bash
mvn exec:java -Dexec.args="<day_number>"
```

### Adding Solutions

1. Open the corresponding day class (e.g., `DayOne.java` for day 1)
2. Implement the `solvePartOne()` method for part 1
3. Implement the `solvePartTwo()` method for part 2
4. Place your input in `src/main/java/io/github/maxBRT/Inputs/day<number>.txt`
5. Use `getInput()` to access the input

The program will automatically:

- Load the correct day class
- Read the input file
- Execute both parts
- Display the results
