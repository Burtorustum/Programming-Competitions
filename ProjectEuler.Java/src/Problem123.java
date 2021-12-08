import Numbers.PrimeNumbers;
import java.math.BigInteger;
import java.util.List;

public class Problem123 {
  public static String run() {
    List<Integer> primes = PrimeNumbers.atkinsSieve(1000000);
    primes.add(0, -1);

    BigInteger min = new BigInteger("10000000000");
    int n = 1;

    while (true) {
      BigInteger temp = BigInteger.TWO.multiply(new BigInteger(n + "")).multiply(new BigInteger(primes.get(n) + ""));
      if (temp.compareTo(min) > 0) {
        return n + "";
      }

      n+=2;
    }
  }

  private static long powerN(long number, int power){
    long res = 1;
    long sq = number;
    while(power > 0){
      if(power % 2 == 1){
        res *= sq;
      }
      sq = sq * sq;
      power /= 2;
    }
    return res;
  }
}
