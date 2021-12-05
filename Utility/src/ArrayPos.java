public class ArrayPos extends Pair<Integer, Integer> {
  ArrayPos(Integer row, Integer col) {
    super(row, col);
  }

  @Override
  public String toString() {
    return "(Row: " + this.x + " Col: " + this.y + ")";
  }
}
