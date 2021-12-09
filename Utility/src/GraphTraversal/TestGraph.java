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

    graph.addNode(new Node<>(0));
    graph.addNode(targetNode);

    for (Node<Integer> n : nodes) {
      graph.addNode(n);
    }

    graph.addEdge(graph.nodes.get(0), targetNode, 1);
    graph.addEdge(graph.nodes.get(0), graph.nodes.get(2), 1);

    /*graph.addEdge(nodes.get(4), nodes.get(0), 200);
    graph.addEdge(nodes.get(7), nodes.get(19), 200);
    graph.addEdge(nodes.get(13), nodes.get(15), 74);
    graph.addEdge(nodes.get(1), nodes.get(2), 13);
    graph.addEdge(nodes.get(0), nodes.get(8), 12);
    graph.addEdge(nodes.get(17), nodes.get(0), 84);*/



    Node<Integer> BFSout = graph.DFSorBFS(targetNode.getData(), false);
    System.out.println("BFS Result: " + BFSout);
    System.out.println(graph);

    graph.unmarkAll();
    System.out.println("--------\n");

    Node<Integer> DFSout = graph.DFSorBFS(targetNode.getData(), true);
    System.out.println("DFS Result: " + DFSout);
    System.out.println(graph);

  }
}
