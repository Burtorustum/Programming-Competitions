import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 extends AProblem {

  public Day7(String fileName) throws IOException {
    super(fileName, "--- Day 7: The Treachery of Whales ---");
  }

  @Override
  String solvePartOne() {
    List<Integer> positions = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt)
        .sorted(Integer::compareTo)
        .collect(Collectors.toList());

    int median = positions.get(positions.size()/2);

    return positions.stream()
        .map((p) -> Math.abs(p - median))
        .reduce(0, Integer::sum)
        .toString();
  }

  @Override
  String solvePartTwo() {
    List<Integer> positions = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt)
        .sorted(Integer::compareTo)
        .collect(Collectors.toList());

    return Stream.iterate(0, (n) -> n + 1)   // stream of sequential ints
    .limit(positions.get(positions.size() - 1))   // limit to max value in given positions
    .map((Integer n) -> positions.stream()        // map each value generated
        .map((Integer i) -> {                     // map each position to its cost w/ current converge point
          int diff = Math.abs(n - i);
          return (diff * (diff + 1)) / 2;
        })
        .reduce(Integer::sum).get())              // reduce to sum of costs for this convergence
        .min(Integer::compareTo)                      // what is the minimum total cost
        .get().toString();
  }
}
