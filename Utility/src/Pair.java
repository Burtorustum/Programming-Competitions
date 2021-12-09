import java.util.Objects;

public record Pair<T, U> (T one, U two) {

  public boolean contains(Object item) {
    return item.equals(one) || item.equals(two);
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
