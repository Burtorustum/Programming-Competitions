package Numbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FigurateNumbers {
  public static List<Integer> genTriangularNums(int maxN) {
    return Stream
        .iterate(1, n -> n+1)
        .limit(maxN)
        .map(FigurateNumbers::triangularNumber)
        .collect(Collectors.toList());
  }

  public static int triangularNumber(int n) {
    return (n * (n+1)) / 2;
  }

  public static boolean isTriangular(int x) {
    return 0 == ((Math.sqrt(x * 8.0 + 1) - 1) / 2.0) % 1;
  }

  public static List<Integer> genPentagonalNums(int maxN) {
    return Stream
        .iterate(1, n -> n+1)
        .limit(maxN)
        .map(FigurateNumbers::pentagonalNumber)
        .collect(Collectors.toList());
  }

  public static int pentagonalNumber(int n) {
    return (n * (3*n-1)) / 2;
  }

  public static boolean isPentagonal(int x) {
    return 0 == ((Math.sqrt(x * 24.0 + 1) + 1) / 6.0) % 1;
  }

  public static List<Integer> genHexagonalNums(int maxN) {
    return Stream
        .iterate(1, n -> n+1)
        .limit(maxN)
        .map(FigurateNumbers::hexagonalNumber)
        .collect(Collectors.toList());
  }

  public static int hexagonalNumber(int n) {
    return n * (2*n-1);
  }

  public static boolean isHexagonal(int x) {
    return 0 == ((Math.sqrt(x * 8.0 + 1) + 1) / 4.0) % 1;
  }
}
