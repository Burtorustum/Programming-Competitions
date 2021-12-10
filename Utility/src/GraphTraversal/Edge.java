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

  // this sucks but w/e. Since nodes handle edge gen this can work fine
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (! (o instanceof Edge<?>)) {
      return false;
    }
    return o.toString().equals(this.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.toString());
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
