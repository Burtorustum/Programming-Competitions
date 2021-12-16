import java.util.HashMap;
import org.ejml.simple.SimpleMatrix;

public class BasicGraphAdjMatrix<T> {
  public final HashMap<T, Integer> nodes;
  public final SimpleMatrix adjMatrix;
  private int counter;

  public BasicGraphAdjMatrix(int baseSize) {
    this.nodes = new HashMap<>();
    this.adjMatrix = new SimpleMatrix(baseSize, baseSize);
    this.counter = 0;
  }

  public void addNode(T n) {
    if (n != null && !nodes.containsKey(n)) nodes.put(n, counter++);
  }

  public void addEdge(T n1, T n2) {
    int row = nodes.get(n1);
    int col = nodes.get(n2);
    this.adjMatrix.set(row, col, 1);
  }
}
