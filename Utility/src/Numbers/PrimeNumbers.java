package Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeNumbers {

  public static boolean basicPrimalityTest(int n) {
    if (n <= 3) {
      return n > 1;
    }

    if ((n % 2 == 0) || (n % 3 == 0)) {
      return false;
    }

    int count = 5;
    while (Math.pow(count, 2) <= n) {
      if (n % count == 0 || n % (count + 2) == 0) {
        return false;
      }
      count += 6;
    }

    return true;
  }

  public static List<Integer> atkinsSieve(int limit)
  {
    if (limit <= 1) { throw new IllegalArgumentException("limit must be greater than 1"); }

    boolean[] sieve = new boolean[limit];

    if (limit > 2) { sieve[2] = true; }
    if (limit > 3) { sieve[3] = true; }

    for (int x = 1; x*x < limit; x++)
    {
      for (int y = 1; y*y < limit; y++)
      {
        int n = (4*x*x) + (y*y);
        if (n <= limit && (n % 12 == 1 || n % 12 == 5))
          sieve[n] = true;

        n = (3*x*x) + (y*y);
        if (n <= limit && n% 12 == 7)
          sieve[n] = true;

        n = (3*x*x) - (y*y);
        if (x > y && n <= limit && n % 12 == 11)
          sieve[n] = true;
      }
    }

    for (int r = 5; r*r < limit; r++)
    {
      if (sieve[r])
      {
        for (int i = r*r; i < limit; i += r)
          sieve[i] = false;
      }
    }

    List<Integer> returning = new ArrayList<>();

    for (int i = 0; i < sieve.length; i++)
    {
      if (sieve[i])
        returning.add(i);
    }

    return returning;
  }
}
