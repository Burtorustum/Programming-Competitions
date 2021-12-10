import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestGraph {
  public static void main(String[] args) {

    List<Node<Integer>> nodes = Stream
        .iterate(1, n -> n+1)
        .limit(10)
        .map(Node::new)
        .collect(Collectors.toList());

    Stream
        .iterate(0, n->n+1)
        .limit(nodes.size())
        .map(nodes::get)
        .reduce((n1, n2) -> {
          n1.connectTwoWay(n2,1);
          return n2;
        });



    Graph<Integer> graph = new Graph<>();
    Node<Integer> targetNode = new Node<>(20);

    graph.addNode(targetNode);

    for (Node<Integer> n : nodes) {
      graph.addNode(n);
    }

    graph.addTwoWayEdge(graph.nodes.get(1), targetNode, 10);
    graph.addTwoWayEdge(graph.nodes.get(1), graph.nodes.get(4), 1);
    graph.addTwoWayEdge(graph.nodes.get(4), targetNode, 1);

    graph.minPath(nodes.get(0), targetNode);
    System.out.println(graph.nodes.stream().map(Node::isMarked).collect(Collectors.toList()));

    //Node<Integer> BFSout = graph.DFSorBFS(targetNode.getData(), false);
    //System.out.println("BFS Result: " + BFSout);
    //System.out.println(graph);

    //graph.unmarkAll();
    //System.out.println("--------\n");

    //Node<Integer> DFSout = graph.DFSorBFS(targetNode.getData(), true);
    //System.out.println("DFS Result: " + DFSout);
    //System.out.println(graph);

  }
}
