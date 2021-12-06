import java.util.Objects;

public class Pair<T, U> {
  final T one;
  final U two;

  public Pair(T one, U two) {
    this.one = one;
    this.two = two;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(one, pair.one) && Objects.equals(two, pair.two);
  }

  @Override
  public int hashCode() {
    return Objects.hash(one, two);
  }

  @Override
  public String toString() {
    return "Pair{" + one + "," + two + '}';
  }
}
