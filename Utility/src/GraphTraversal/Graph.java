import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Graph<T> {
  final List<Node<T>> nodes;

  public Graph() {
    this.nodes = new ArrayList<>();
  }

  public void addNode(Node<T> n) {
    if (this.nodes.contains(n)) {
      throw new IllegalArgumentException("give node already in graph");
    }
    this.nodes.add(n);
  }

  public void addEdge(Node<T> a, Node<T> b, int weight) {
    if (!this.nodes.contains(a) || !this.nodes.contains(b)) {
      throw new IllegalArgumentException("A or B not in this graph");
    }
    a.connectTwoWay(b, weight);
  }

  public void unmarkAll() {
    this.nodes.forEach(Node::unmark);
  }

  //public Node<T> randomSearch(T target) {}

  //public enum DFSOrder {PRE, REV_PRE, POST, REV_POST}

  public Node<T> DFSorBFS(T target, boolean DFS) {
    Deque<Node<T>> toExplore = new ArrayDeque<>();

    if (DFS) {
      toExplore.push(nodes.get(0));
    } else {
      toExplore.add(nodes.get(0));
    }
    nodes.get(0).mark();

    while (!toExplore.isEmpty()) {
      Node<T> cur;

      if (DFS) {
        cur = toExplore.pop();
      } else {
        cur = toExplore.remove();
      }

      if (cur.getData().equals(target)) {
        return cur;
      }

      for (Node<T> adj : cur.neighbors()) {
        if (!adj.isMarked()) {
          adj.mark();
          if (DFS) {
            toExplore.push(adj);
          } else {
            toExplore.add(adj);
          }
        }
      }
    }

    return null;
  }


  //public List<Node<T>> minPath(Node<T> source, Node<T> target) {}

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    for (Node<T> n  : nodes) {
      ret.append(n.toString()).append("\n");
    }
    return ret.toString();
  }
}
