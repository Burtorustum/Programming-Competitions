import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day15 extends AProblem {

  public Day15(String fileName) throws IOException {
    super(fileName, "--- Day 15: Chiton ---");
  }

  @Override
  String solvePartOne() {
    Graph<ArrayPos> graph = genGrid(lines.size(), lines.get(0).length());
    List<Node<ArrayPos>> path = graph.minPath(graph.nodes.get(0), graph.nodes.get(graph.nodes.size()-1));

    int sum = 0;
    for (int i = path.size()-1; i > 0; i--) {
      int weight = path.get(i).getEdgeWith(path.get(i-1));
      sum += weight;
    }

    return "" + sum;
  }

  @Override
  String solvePartTwo() {
    long initialTime = System.currentTimeMillis();

    Graph<ArrayPos> graph = genGridB(lines.size(), lines.get(0).length());

    System.out.println("Gen TIME: " + (System.currentTimeMillis() - initialTime));
    initialTime = System.currentTimeMillis();

    List<Node<ArrayPos>> path = graph.minPath(graph.nodes.get(0), graph.nodes.get(graph.nodes.size()-1));

    System.out.println("Dijkstra TIME: " + (System.currentTimeMillis() - initialTime));


    int sum = 0;
    for (int i = path.size()-1; i > 0; i--) {
      int weight = path.get(i).getEdgeWith(path.get(i-1));
      sum += weight;
    }

    return "" + sum;
  }

  private Graph<ArrayPos> genGrid(int numRows, int numCols) {
    List<List<Node<ArrayPos>>> gridNodes = new ArrayList<>();
    Graph<ArrayPos> grid = new Graph<>();

    for (int row = 0; row < numRows; row++) {
      gridNodes.add(new ArrayList<>());
      for (int col = 0; col < numCols; col++) {
        Node<ArrayPos> cur = new Node<>(new ArrayPos(row, col));
        gridNodes.get(row).add(cur);
        grid.addNode(cur);
      }
    }
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {


        int weight = Integer.parseInt(lines.get(row).charAt(col) + "");
        if (col != 0) {
          gridNodes.get(row).get(col-1).connect(gridNodes.get(row).get(col), weight);
        }
        if (row != 0) {
          gridNodes.get(row-1).get(col).connect(gridNodes.get(row).get(col), weight);
        }
        if (col != gridNodes.get(row).size()-1) {
          gridNodes.get(row).get(col+1).connect(gridNodes.get(row).get(col), weight);
        }
        if (row != gridNodes.size()-1) {
          gridNodes.get(row+1).get(col).connect(gridNodes.get(row).get(col),weight);
        }
      }
    }

    return grid;
  }

  private Graph<ArrayPos> genGridB(int numRows, int numCols) {
    List<List<Node<ArrayPos>>> gridNodes = new ArrayList<>();
    Graph<ArrayPos> grid = new Graph<>();

    for (int row = 0; row < numRows*5; row++) {
      gridNodes.add(new ArrayList<>());
      for (int col = 0; col < numCols*5; col++) {
        Node<ArrayPos> cur = new Node<>(new ArrayPos(row, col));
        gridNodes.get(row).add(cur);
        grid.addNode(cur);
      }
    }
    //System.out.println("HERE");
    //gridNodes.forEach(System.out::println);

    for (int row = 0; row < gridNodes.size(); row++) {
      for (int col = 0; col < gridNodes.get(row).size(); col++) {

        int baseWeight = Integer.parseInt(lines.get(row % numRows).charAt(col % numCols) + "");
        int dist = (row/numRows) + (col/numCols);

        //System.out.println("ROW: " + row +" COL: " + col);
        //System.out.println("DIST: " + dist);

        int weight = baseWeight + dist;
        if (weight > 9) {
            weight -= 9;
        }

        if (col != 0) {
          gridNodes.get(row).get(col-1).connect(gridNodes.get(row).get(col), weight);
        }
        if (row != 0) {
          gridNodes.get(row-1).get(col).connect(gridNodes.get(row).get(col), weight);
        }
        if (col != gridNodes.get(row).size()-1) {
          gridNodes.get(row).get(col+1).connect(gridNodes.get(row).get(col), weight);
        }
        if (row != gridNodes.size()-1) {
          gridNodes.get(row+1).get(col).connect(gridNodes.get(row).get(col),weight);
        }
      }
    }

    return grid;
  }
}
