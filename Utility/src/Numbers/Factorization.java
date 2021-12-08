package Numbers;

import java.util.ArrayList;
import java.util.List;

public class Factorization {

  public static List<Integer> getUniqueDivisors(int n)
  {
    List<Integer> l = new ArrayList<>();

    for (int i = 1; i <= Math.sqrt(n); i++)
    {
      if (n % i == 0)
      {
        if (n / i == i) {
          l.add(i);
        } else {
          l.add(n/i);
          l.add(i);
        }
      }
    }

    return l;
  }

}
