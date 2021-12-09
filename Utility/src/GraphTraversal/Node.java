import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Node<T> {
  private final T data;
  private final List<Edge<T>> edgeList;

  private boolean marked = false;

  public Node(T data) {
    this.data = data;
    this.edgeList = new ArrayList<>();
  }

  public Node(T data, List<Edge<T>> edgeList) {
    this.data = data;
    this.edgeList = edgeList;
  }

  public T getData() {
    return this.data;
  }

  public List<Edge<T>> getEdgeList() {
    return this.edgeList;
  }

  public boolean isMarked() {
    return marked;
  }

  public boolean isNeighberOf(Node<T> n) {
    return this.edgeList.stream().anyMatch(e -> e.contains(n));
  }

  public List<Node<T>> neighbors() {
    return this.edgeList.stream()
        .map(e -> e.getOther(this))
        .collect(Collectors.toList());
  }

  /**
   * Mark this node
   */
  public void mark() {
    this.marked = true;
  }

  public void unmark() {
    this.marked = false;
  }

  /**
   *  Create one-way edge from this Node to given node. Overwrites edge if it already exists.
   * @param other Node to connect this to
   * @param weight Weight of edge between the two nodes
   */
  public void connect(Node<T> other, int weight) {
    this.edgeList.add(new Edge<T>(weight, this, other));
  }

  public void connect(Node<T> other) {
    this.connect(other, 1);
  }

  /**
   * Create a two-way edge between this Node and given
   * @param other Node to connect this to
   * @param weight Weight of edge between the two nodes
   */
  public void connectTwoWay(Node<T> other, int weight) {
    this.connect(other, weight);
    other.connect(this, weight);
  }

  public void connectTwoWay(Node<T> other) {
    this.connect(other);
    other.connect(this);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (o instanceof Node<?>) {
      return ((Node<?>) o).getData().equals(this.data)
          && ((Node<?>) o).getEdgeList().equals(this.edgeList)
          && ((Node<?>) o).isMarked() == (this.marked);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, edgeList, marked);
  }

  @Override
  public String toString() {
    return "Node{" +
        "data=" + data +
        ", connects to: [" + edgeList.stream().map(e -> e.getOther(this).getData() + "").reduce((a, b) -> a + " " + b).orElse("") +
        "], marked= " + marked +
        '}';
  }
}
