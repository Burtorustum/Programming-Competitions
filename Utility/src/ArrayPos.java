import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayPos {
  final int row;
  final int col;

  ArrayPos(Integer row, Integer col) {
    this.row = row;
    this.col = col;
  }

  /**
   *
   * @param numRows the total number of rows (array.length())
   * @param numCols the total number of cols (array[row].length())
   * @param includeDiagonals whether to include diagonal adjacent positions
   * @return A list of all valid adjacent ArrayPos to this one
   */
  public List<ArrayPos> getAdjacent(int numRows, int numCols, boolean includeDiagonals) {
    List<ArrayPos> adj = new ArrayList<>();
    adj.add(getLeft());
    adj.add(getRight(numCols));
    adj.add(getDown(numRows));
    adj.add(getUp());
    if (includeDiagonals) {
      adj.add(getLD(numRows));
      adj.add(getLU());
      adj.add(getRD(numRows, numCols));
      adj.add(getRU(numCols));
    }
    return adj;
  }

  ArrayPos getLeft() {
    if (col != 0) {
      return new ArrayPos(this.row, col-1);
    }
    return null;
  }

  ArrayPos getRight(int numCols) {
    if (col != numCols - 1) {
      return new ArrayPos(row, col+1);
    }
    return null;
  }
  ArrayPos getUp() {
    if (row != 0) {
      return new ArrayPos(row-1, col);
    }
    return null;
  }
  ArrayPos getDown(int numRows) {
    if (row != numRows - 1) {
      return new ArrayPos(row+1, col);
    }
    return null;
  }

  ArrayPos getLU() {
    ArrayPos L = this.getLeft();
    ArrayPos U = this.getUp();
    if (L != null && U != null) {
      return new ArrayPos(U.row, L.col);
    }
    return null;
  }

  ArrayPos getLD(int numRows) {
    ArrayPos L = this.getLeft();
    ArrayPos D = this.getDown(numRows);
    if (L != null && D != null) {
      return new ArrayPos(D.row, L.col);
    }
    return null;
  }

  ArrayPos getRU(int numCols) {
    ArrayPos R = this.getRight(numCols);
    ArrayPos U = this.getUp();
    if (R != null && U != null) {
      return new ArrayPos(U.row, R.col);
    }
    return null;
  }

  ArrayPos getRD(int numRows, int numCols) {
    ArrayPos R = this.getRight(numCols);
    ArrayPos D = this.getDown(numRows);
    if (R != null && D != null) {
      return new ArrayPos(D.row, R.col);
    }
    return null;
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
