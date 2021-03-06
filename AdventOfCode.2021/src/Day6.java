import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Consumer;

public class Day6 extends AProblem {

  public Day6(String fileName) throws IOException {
    super(fileName, "--- Day 6: Lanternfish ---");
  }

  @Override
  String solvePartOne() {
    return solve(80).toString();
  }

  @Override
  String solvePartTwo() {
    return solve(256).toString();
  }

  private BigInteger solve(int numDays) {
    BigInteger[] fishArr = new BigInteger[9];
    Arrays.fill(fishArr, 0, 9, BigInteger.ZERO);

    Arrays.stream(lines.get(0).split(","))
        .map(BigInteger::new)
        .forEach(new ConsumeFish(fishArr));

    for (int day = 1; day <= numDays; day++) {
      iterate(fishArr);
    }

    BigInteger count = BigInteger.ZERO;

    for (BigInteger i : fishArr) {
      count = count.add(i);
    }

    return count;
  }

  private void iterate(BigInteger[] fishArr) {
    BigInteger temp = fishArr[0];

    fishArr[0] = fishArr[1];
    fishArr[1] = fishArr[2];
    fishArr[2] = fishArr[3];
    fishArr[3] = fishArr[4];
    fishArr[4] = fishArr[5];
    fishArr[5] = fishArr[6];
    fishArr[6] = fishArr[7].add(temp);
    fishArr[7] = fishArr[8];
    fishArr[8] = temp;
  }

  private record ConsumeFish(BigInteger[] arr) implements Consumer<BigInteger> {
    @Override
    public void accept(BigInteger bigInteger) {
      arr[bigInteger.intValue()] = arr[bigInteger.intValue()].add(BigInteger.ONE);
    }
  }

}
