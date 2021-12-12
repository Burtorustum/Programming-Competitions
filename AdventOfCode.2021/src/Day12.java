import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 extends AProblem {

  public Day12(String fileName) throws IOException {
    super(fileName, "--- 12 ---");
  }

  @Override
  String solvePartOne() {
    HashMap<String, List<String>> map = new HashMap<>();
    Graph<String> graph = genGraph(map);

    List<List<Node<String>>> allPaths = new ArrayList<>();
    List<Node<String>> curPath = new ArrayList<>();

    graph.aocProblem12A(graph.nodes.get(graph.nodes.stream().map(Node::getData).toList().indexOf("start")), "end", curPath, allPaths);

    return allPaths.size() + "";
  }

  @Override
  String solvePartTwo() {
    HashMap<String, List<String>> map = new HashMap<>();
    Graph<String> graph = genGraph(map);

    List<List<Node<String>>> allPaths = new ArrayList<>();
    List<Node<String>> curPath = new ArrayList<>();

    graph.aocProblem12B(graph.nodes.get(graph.nodes.stream().map(Node::getData).toList().indexOf("start")), "end", curPath, allPaths, false);

    return "" +
        new HashSet<>(
        allPaths.stream()
            .map(r -> r.stream().map(Node::getData).toList())
            .collect(Collectors.toSet()))
        .size();
  }

  Graph<String> genGraph(HashMap<String, List<String>> map) {
    Graph<String> graph = new Graph<>();

    for (String line : lines) {
      String[] connect = line.split("-");
      if (!map.containsKey(connect[0])) map.put(connect[0], new ArrayList<>());
      if (!map.containsKey(connect[1])) map.put(connect[1], new ArrayList<>());

      map.get(connect[0]).add(connect[1]);
      map.get(connect[1]).add(connect[0]);
    }

    List<Node<String>> nodeList = map.keySet().stream().map(Node::new).toList();

    for (Node<String> n : nodeList) {
      for (String s : map.get(n.getData())) {
        Node<String> other = nodeList.get(nodeList.stream().map(Node::getData).toList().indexOf(s));
        n.connectTwoWay(other);
      }
      graph.addNode(n);
    }

    return graph;
  }
}
