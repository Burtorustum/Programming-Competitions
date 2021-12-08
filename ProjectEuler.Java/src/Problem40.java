import java.math.BigInteger;
import java.util.stream.Stream;

public class Problem40 {

  public static String run() {
    String s = Stream
        .iterate(0, n -> n + 1)
        .limit(1000001)
        .map(n -> Integer.toString(n))
        .reduce((a, b) -> a + b)
        .get();

    return ""
        + s.charAt(1)
        + s.charAt(10)
        + s.charAt(100)
        + s.charAt(1000)
        + s.charAt(10000)
        + s.charAt(100000)
        + s.charAt(1000000);
  }
}
