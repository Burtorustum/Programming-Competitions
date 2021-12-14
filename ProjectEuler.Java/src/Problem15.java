import java.util.ArrayList;
import java.util.List;

public class Problem15 {
  public static int run() {
    //TODO: if implement graph as adjacency matrix can do this wayyy faster by taking powers of the list
    Graph<ArrayPos> grid = genGrid(21, 21);
    return grid.numPathsBFS(grid.nodes.get(0),grid.nodes.get(grid.nodes.size()-1).getData());
  }

  private static Graph<ArrayPos> genGrid(int numRows, int numCols) {
    List<List<Node<ArrayPos>>> gridNodes = new ArrayList<>();
    Graph<ArrayPos> grid = new Graph<>();

    for (int row = 0; row < numRows; row++) {
      gridNodes.add(new ArrayList<>());
      for (int col = 0; col < numCols; col++) {
        Node<ArrayPos> cur = new Node<>(new ArrayPos(row, col));
        gridNodes.get(row).add(cur);
        if (col != 0) {
          gridNodes.get(row).get(col-1).connect(gridNodes.get(row).get(col));
        }
        if (row != 0) {
          gridNodes.get(row-1).get(col).connect(gridNodes.get(row).get(col));
        }
        grid.addNode(cur);
      }
    }

    System.out.println(grid);

    return grid;
  }

}

