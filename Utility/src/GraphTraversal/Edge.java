import java.util.Objects;

public record Edge<T>(int weight, Node<T> nodeA, Node<T> nodeB) {
  public Pair<Node<T>, Node<T>> getNodes() {
    return new Pair<>(nodeA, nodeB);
  }

  public Node<T> getOther(Node<T> node) {
    if (node.equals(nodeA)) {
      return nodeB;
    }
    if (node.equals(nodeB)) {
      return nodeA;
    }
    throw new IllegalArgumentException("Neither A nor B is the given node");
  }

  public boolean contains(Node<T> n) {
    return n.equals(nodeA) || n.equals(nodeB);
  }

  // TODO this sucks but w/e?
  @Override
  public boolean equals(Object o) {
    return this == o;
  }

  @Override
  public int hashCode() {
    return Objects.hash(weight, nodeA, nodeB);
  }

  @Override
  public String toString() {
    return "Edge{" +
        "weight=" + weight +
        ", nodeA=" + nodeA +
        ", nodeB=" + nodeB +
        '}';
  }
}
