import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
  public static void main(String[] args) throws IOException {
    List<AProblem> problems = new ArrayList<>();
    problems.add(new Day1("data1.txt"));
    problems.add(new Day2("data2.txt"));

    for (AProblem p : problems) {
      System.out.println(p.name());
      System.out.println("Part 1: " + p.solvePartOne());
      System.out.println("Part 2: " + p.solvePartTwo());
    }
  }
}
