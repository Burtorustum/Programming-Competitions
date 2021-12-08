import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Problem81 {
  public static String run() {
    List<String> lines = new ArrayList<>();
    Path path = Paths.get("ProjectEuler.java\\src\\Problem81Data.txt");

    try {
      lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<List<Problem18_67.Vertex>> graph = genGraph(lines);
    return Problem18_67.dijkstras(graph, false).stream()
        .map(v -> v.val)
        .reduce(Integer::sum)
        .get().toString();
  }

  public static List<List<Problem18_67.Vertex>> genGraph(List<String> lines) {
    List<List<Problem18_67.Vertex>> made = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      String[] split = lines.get(i).split(",");
      made.add(new ArrayList<>());

      for (int j = 0; j < split.length; j++) {
        Problem18_67.Vertex v = new Problem18_67.Vertex(Integer.parseInt(split[j]), i);

        if (i > 0) {
          v.addTwoWayAdjacency(made.get(i-1).get(j));
        }
        if (j > 0) {
          v.addTwoWayAdjacency(made.get(i).get(j-1));
        }

        made.get(i).add(v);
      }
      //System.out.println(made);
    }

    return made;
  }

  public static List<Problem18_67.Vertex> dijkstras(List<List<Problem18_67.Vertex>> graph, Problem18_67.Vertex src) {
    List<Problem18_67.Vertex> graphFlat = graph.stream().flatMap(List::stream).collect(Collectors.toList());

    HashMap<Problem18_67.Vertex, Integer> dist = new HashMap<>();
    HashMap<Problem18_67.Vertex, Problem18_67.Vertex> prev = new HashMap<>();

    Comparator<Problem18_67.Vertex> comparator =  new MinCompareDijkstra(dist);
    PriorityQueue<Problem18_67.Vertex> priorityQueue = new PriorityQueue<>(comparator);
    List<Problem18_67.Vertex> evaluated = new ArrayList<>();

    for (Problem18_67.Vertex v : graphFlat) {
      dist.put(v, Integer.MAX_VALUE);
      prev.put(v, null);
    }

    dist.put(src, 0);
    priorityQueue.add(src);
    evaluated.add(src);

    while (!priorityQueue.isEmpty()) {
      Problem18_67.Vertex u = priorityQueue.remove();

      for (Problem18_67.Vertex v : u.adjacent) {
        int alt = dist.get(u) + v.val;
        if (alt <= dist.get(v)) {
          dist.put(v, alt);
          prev.put(v, u);
          if (!evaluated.contains(v)) {
            System.out.println(v);
            priorityQueue.add(v);
            evaluated.add(v);
          }
        }
      }
    }

    List<Problem18_67.Vertex> res = new ArrayList<>();
    Problem18_67.Vertex last = graph.get(graph.size()-1).stream()
        .reduce((a, b) -> (dist.get(a) < dist.get(b) ? a : b))
        .get();

    while (last != null) {
      res.add(0, last);
      last = prev.get(last);
    }

    System.out.println(res.stream().map(v -> v.val).collect(Collectors.toList()));

    return res;
  }

  private static record MinCompareDijkstra(HashMap<Problem18_67.Vertex, Integer> dist) implements Comparator<Problem18_67.Vertex> {
    @Override
    public int compare(Problem18_67.Vertex o1, Problem18_67.Vertex o2) {
      return dist.get(o1) - dist.get(o2);
    }
  }

}
