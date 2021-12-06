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
}
