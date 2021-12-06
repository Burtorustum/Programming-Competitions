import java.util.List;
import java.util.Objects;

public class Point2D {
  final int x;
  final int y;

  Point2D(List<Integer> pair) {
    if (pair.size() != 2) {
      throw new IllegalArgumentException("GIVEN LIST MUST ONLY CONTAIN 2 INTS (POINT2D)");
    }
    this.x = pair.get(0);
    this.y = pair.get(1);
  }

  Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point2D point2D = (Point2D) o;
    return x == point2D.x && y == point2D.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
      return "(" + this.x + "," + this.y + ")";
  }
}
