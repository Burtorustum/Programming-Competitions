import java.util.Objects;

public class ArrayPos {
  final int row;
  final int col;
  ArrayPos(Integer row, Integer col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public String toString() {
    return "(Row: " + this.row + " Col: " + this.col + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrayPos arrayPos = (ArrayPos) o;
    return row == arrayPos.row && col == arrayPos.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
