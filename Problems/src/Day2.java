import java.io.IOException;

public class Day2 extends AProblem {

  public Day2(String fileName) throws IOException {
    super(fileName);
  }

  @Override
  String solvePartOne() {
    int depth = 0;
    int horizontal = 0;

    for (String line : lines) {
      String[] split = line.split(" ");
      String command = split[0];
      int num = Integer.parseInt(split[1]);

      switch (command) {
        case ("forward") -> horizontal += num;
        case ("down") -> depth += num;
        case ("up") -> depth -= num;
      }
    }

    return (depth * horizontal) + "";
  }

  @Override
  String solvePartTwo() {
    int depth = 0;
    int horizontal = 0;
    int aim = 0;

    for (String line : lines) {
      String[] split = line.split(" ");
      String command = split[0];
      int num = Integer.parseInt(split[1]);

      switch (command) {
        case ("forward") -> {
          horizontal += num;
          depth += aim * num;
        }
        case ("down") -> aim += num;
        case ("up") -> aim -= num;
      }
    }

    return (depth * horizontal) + "";
  }

  @Override
  String name() {
    return "--- Day 2: Dive! ---";
  }
}
