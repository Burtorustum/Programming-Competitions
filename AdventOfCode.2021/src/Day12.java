import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    graph.numPathsProb122(graph.nodes.get(graph.nodes.stream().map(Node::getData).toList().indexOf("start")), "end", curPath, allPaths);
    for (List<Node<String>> path : allPaths) {
      System.out.println(path.stream().map(Node::getData).toList());
    }
    return allPaths.size() + "";
  }

  @Override
  String solvePartTwo() {


    return null;
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
