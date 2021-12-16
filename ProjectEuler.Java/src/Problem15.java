import org.ejml.simple.SimpleMatrix;

public class Problem15 {
  public static String run() {
    BasicGraphAdjMatrix<ArrayPos> grid = genGrid(21, 21);
    SimpleMatrix matrix = grid.adjMatrix;

    int startPos = grid.nodes.get(new ArrayPos(0,0));
    int endPos = grid.nodes.get(new ArrayPos(20,20));

    int count = 0;
    for (int i = 0; i <= 100; i++) {
      count += matrix.get(startPos, endPos);
      matrix = matrix.mult(matrix);
    }

    return "" +  count;
  }

  private static BasicGraphAdjMatrix<ArrayPos> genGrid(int numRows, int numCols) {
    BasicGraphAdjMatrix<ArrayPos> grid = new BasicGraphAdjMatrix<>(numRows*numCols);

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        ArrayPos cur = new ArrayPos(row, col);
        grid.addNode(cur);
        for (ArrayPos p : cur.getAdjacent(numRows, numCols, true, false, true, false)) {
          grid.addEdge(p, cur);
        }

      }
    }

    //System.out.println(grid.adjMatrix);

    return grid;
  }

}

