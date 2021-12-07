import java.io.IOException;

public class Day1 extends AProblem {

  public Day1(String fileName) throws IOException {
    super(fileName, "--- Day 1: Sonar Sweep ---");
  }

  @Override
  public String solvePartOne() {
    int count = 0;

    for(int i = 0; i < lines.size()-1; i++) {
      int low = Integer.parseInt(lines.get(i));
      int high = Integer.parseInt(lines.get(i+1));

      if (high > low) {
        count++;
      }
    }
    return count + "";
  }

  @Override
  String solvePartTwo() {
    int count = 0;

    for (int i = 0; i < lines.size()-3; i++) {
      int one = Integer.parseInt(lines.get(i));
      int two = Integer.parseInt(lines.get(i+1));
      int three = Integer.parseInt(lines.get(i+2));
      int four = Integer.parseInt(lines.get(i+3));
      int sum1 = one + two + three;
      int sum2 = two + three + four;

      if (sum2 > sum1) {
        count++;
      }

    }

    return count + "";
  }

}
