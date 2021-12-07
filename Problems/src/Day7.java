import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    int count = 0;
    for (Integer i : positions) {
      count += Math.abs(i - median);
    }
    return count + "";
  }

  @Override
  String solvePartTwo() {
    List<Integer> positions = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt)
        .sorted(Integer::compareTo)
        .collect(Collectors.toList());

    BigInteger least = new BigInteger("11111111111111111111111");

    for (int i = 0; i < positions.stream().max(Integer::compareTo).get(); i++) {
      BigInteger temp = BigInteger.ZERO;

      for (Integer pos : positions) {
        BigInteger diff = new BigInteger(Math.abs(i - pos) + "");
        temp = temp.add((diff.multiply(diff.add(BigInteger.ONE))).divide(BigInteger.TWO));
      }

      if (temp.compareTo(least) < 0) {
        least = temp;
      }
    }

    return least.toString();
  }
}
