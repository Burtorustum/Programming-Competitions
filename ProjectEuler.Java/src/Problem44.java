import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem44 {
  public static void main(String[] args) {
    List<Integer> pentagonals = Stream
        .iterate(1, n -> n+1)
        .limit(5000)
        .map(n -> n * (3 * n - 1) / 2)
        .collect(Collectors.toList());

    List<Integer> possibilities = pentagonals.stream()
        .map(n -> pentagonals.stream()
                .filter(i -> isPentagonal(n + i) && isPentagonal(Math.abs(n - i)))
                .collect(Collectors.toList()))
        .filter(l -> l.size() > 0)
        .flatMap(List::stream)
        .collect(Collectors.toList());

    System.out.println(possibilities);
    System.out.println(possibilities.get(0) - possibilities.get(1));
  }

  private static boolean isPentagonal(int x) {
    return (Math.sqrt(x * 24.0 + 1) + 1) / 6.0 % 1 == 0;
  }
}
