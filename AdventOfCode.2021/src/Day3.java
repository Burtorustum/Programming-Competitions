import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day3 extends AProblem {

  public Day3(String fileName) throws IOException {
    super(fileName, "--- Day 3: Binary Diagnostic ---");
  }

  @Override
  String solvePartOne() {
    StringBuilder gamma = new StringBuilder();
    StringBuilder epsilon = new StringBuilder();

    for (int i = 0; i < lines.get(0).length(); i++) {
      int zeroCount = 0;
      int oneCount = 0;

      for (String s : lines) {
        if (s.charAt(i) == '0') {
          zeroCount++;
        } else {
          oneCount++;
        }
      }

      gamma.append(oneCount > zeroCount ? 1 : 0);
      epsilon.append(zeroCount > oneCount ? 1 : 0);
    }

    return (Integer.parseInt(gamma.toString(), 2)
        * Integer.parseInt(epsilon.toString(), 2))
        + "";
  }

  @Override
  String solvePartTwo() {
    List<String> oxygenAccepted = new ArrayList<>(lines);
    List<String> co2Accepted = new ArrayList<>(lines);
    char oxygenMostCommon;
    char co2LeastCommon;

    int position = 0;
    while (oxygenAccepted.size() != 1 || co2Accepted.size() != 1) {

      int zeroCount = 0;
      int oneCount = 0;

      for (String s : oxygenAccepted) {
        if (s.charAt(position) == '0') {
          zeroCount++;
        } else {
          oneCount++;
        }
      }
      oxygenMostCommon = zeroCount > oneCount ? '0' : '1';

      zeroCount = 0;
      oneCount = 0;

      for (String s : co2Accepted) {
        if (s.charAt(position) == '0') {
          zeroCount++;
        } else {
          oneCount++;
        }
      }
      co2LeastCommon = zeroCount > oneCount ? '1' : '0';

      if (oxygenAccepted.size() > 1) {
        oxygenAccepted = oxygenAccepted.stream()
            .filter(new FilterPredicate(position, oxygenMostCommon))
            .collect(Collectors.toList());
      }

      if (co2Accepted.size() > 1) {
        co2Accepted = co2Accepted.stream()
            .filter(new FilterPredicate(position, co2LeastCommon))
            .collect(Collectors.toList());
      }

      position++;
    }

    return (Integer.parseInt(oxygenAccepted.get(0), 2)
        * Integer.parseInt(co2Accepted.get(0), 2))
        + "";
  }

  private record FilterPredicate(int position, char mostCommon) implements Predicate<String> {
    @Override
    public boolean test(String s) {
      return s.charAt(position) == mostCommon;
    }
  }

}
