import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
  public static void main(String[] args) throws IOException {
    List<AProblem> problems = new ArrayList<>();
    //problems.add(new Day1(nameFile(1)));
    //problems.add(new Day2(nameFile(2)));
    //problems.add(new Day3(nameFile(3)));
    //problems.add(new Day4(nameFile(4)));
    //problems.add(new Day5(nameFile(5)));
    problems.add(new Day6(nameFile(6)));

    for (AProblem p : problems) {
      System.out.println(p.name());
      System.out.println("Part 1: " + p.solvePartOne());
      System.out.println("Part 2: " + p.solvePartTwo());
    }
  }

  private static String nameFile(int day) {
    return "data" + day + ".txt";
  }
}
