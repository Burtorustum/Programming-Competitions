import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph<T> {
  final List<Node<T>> nodes;

  public Graph() {
    this.nodes = new ArrayList<>();
  }

  public void addNode(Node<T> n) {
    if (this.nodes.contains(n)) {
      throw new IllegalArgumentException("given node already in graph");
    }
    this.nodes.add(n);
  }

  public void addTwoWayEdge(Node<T> a, Node<T> b, int weight) {
    if (!this.nodes.contains(a) || !this.nodes.contains(b)) {
      throw new IllegalArgumentException("A or B not in this graph");
    }
    a.connectTwoWay(b, weight);
  }

  public void addEdge(Node<T> start, Node<T> end, int weight) {
    if (!this.nodes.contains(start) || !this.nodes.contains(end)) {
      throw new IllegalArgumentException("A or B not in this graph");
    }
    start.connect(end, weight);
  }

  public void unmarkAll() {
    this.nodes.forEach(Node::unmark);
  }

  public Node<T> DFSorBFS(Node<T> src, T target, boolean DFS) {
    Deque<Node<T>> toExplore = new ArrayDeque<>();

    if (DFS) {
      toExplore.push(src);
    } else {
      toExplore.add(src);
    }
    src.mark();

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

    return null; // If the data doesn't exist in this graph
  }

  public void aocProblem12A(Node<String> src, String target, List<Node<String>> curPath, List<List<Node<String>>> allPaths) {
    if (src.isMarked()) {
      return;
    }
    if (!Character.isUpperCase(src.getData().charAt(0))) {
      src.mark();
    }
    curPath.add(src);

    if (src.getData().equals(target)) {
      allPaths.add(new ArrayList<>(curPath));
      src.unmark();
      curPath.remove(curPath.size()-1);
      return;
    }
    for (Node<String> adj : src.neighbors()) {
      aocProblem12A(adj, target, curPath, allPaths);
    }
    curPath.remove(curPath.size()-1);
    src.unmark();
  }

  public void aocProblem12B(Node<String> src, String target, List<Node<String>> curPath, List<List<Node<String>>> allPaths, boolean prevUnmarked) {
    if (src.isMarked()) {
      return;
    }

    if (!Character.isUpperCase(src.getData().charAt(0))) {
      src.mark();
    }

    curPath.add(src);

    if (src.getData().equals(target)) {
      allPaths.add(new ArrayList<>(curPath));
      src.unmark();
      curPath.remove(curPath.size()-1);
      return;
    }

    for (Node<String> adj : src.neighbors()) {
      aocProblem12B(adj, target, curPath, allPaths, prevUnmarked);
    }

    if (!Character.isUpperCase(src.getData().charAt(0))
        && !src.getData().equals("start")
        && !prevUnmarked)
    {
      src.unmark();
      for (Node<String> adj : src.neighbors()) {
        aocProblem12B(adj, target, curPath, allPaths, true);
      }
    }

    curPath.remove(curPath.size()-1);
    src.unmark();
  }

  public List<Node<T>> minPath(Node<T> source, Node<T> target) {

    if (!this.nodes.contains(source) || !this.nodes.contains(target)) {
      throw new IllegalArgumentException("Given source or target is not in graph");
    }

    HashMap<Node<T>, Integer> dist = new HashMap<>();
    HashMap<Node<T>, Node<T>> prev = new HashMap<>();

    Comparator<Node<T>> comparator = new MinCompare(dist);
    PriorityQueue<Node<T>> searchQueue = new PriorityQueue<>(comparator);

    for (Node<T> n : this.nodes) {
      dist.put(n, Integer.MAX_VALUE);
    }

    dist.put(source, 0);
    searchQueue.add(source);
    source.mark();

    while (!searchQueue.isEmpty()) {
      Node<T> root = searchQueue.remove();

      // we only need to fine ONE of the shortest paths
      if (root.equals(target)) {
        break;
      }

      for (Edge<T> outEdge : root.getEdgeList()) {

        int alt = dist.get(root) + outEdge.weight();
        Node<T> adj = outEdge.getOther(root);

        if (alt <= dist.get(adj)) {
          dist.put(adj, alt);
          prev.put(adj, root);

          if (!adj.isMarked()) {
            searchQueue.add(adj);
            adj.mark();
          }
        }
      }
    }
    List<Node<T>> path = new ArrayList<>();

    System.out.println("SOURCE: " + source);
    System.out.println("TARGET: " + target);

    while (target != null) {
      path.add(target);
      target = prev.get(target);
    }


    System.out.println("PATH: " + path);
    return path;
  }

  private class MinCompare implements Comparator<Node<T>> {
    private final HashMap<Node<T>, Integer> dist;

    public MinCompare(HashMap<Node<T>, Integer> dist) {
      this.dist = dist;
    }

    @Override
    public int compare(Node<T> o1, Node<T> o2) {
      return dist.get(o1) - dist.get(o2);
    }
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    for (Node<T> n  : nodes) {
      ret.append(n.toString()).append("\n");
    }
    return ret.toString();
  }
}
