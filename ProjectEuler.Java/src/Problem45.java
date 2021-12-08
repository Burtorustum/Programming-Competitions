import Numbers.FigurateNumbers;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem45 {
  public static void main(String[] args) {

    List<Integer> answer = Stream
        .iterate(1, n -> n+1)
        .limit(10000000)
        .map(n -> n * (2*n-1)) // gen hexagonal nums
        .filter(n -> FigurateNumbers.isPentagonal(n) && FigurateNumbers.isTriangular(n))
        .collect(Collectors.toList());


    System.out.println(answer);
  }


}
