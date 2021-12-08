import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Problem18_67 {
  public static String run() {
    
    List<String> lines = new ArrayList<>();
    Path path = Paths.get("ProjectEuler.java\\src\\Problem67Data.txt");
    
    try {
      lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<List<Vertex>> graph = genGraph(lines);
    return dijkstras(graph, false).stream()
        .map(v -> v.val)
        .reduce(Integer::sum)
        .get().toString();
  }

  public static class Vertex {
    final List<Vertex> adjacent;
    final int val;
    final int level;

    public Vertex(int val, int level) {
      this.adjacent = new ArrayList<>();
      this.val = val;
      this.level = level;
    }

    public void addTwoWayAdjacency(Vertex other) {
      this.adjacent.add(other);
      other.adjacent.add(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Vertex vertex = (Vertex) o;
      // TODO: this is why we might use edges for adjacency instead of node but w/e
      List<Integer> adjacentLevels = adjacent.stream().map(v -> v.level).collect(Collectors.toList());
      List<Integer> otherAdjLevels = vertex.adjacent.stream().map(v -> v.level).collect(Collectors.toList());
      List<Integer> adjacentInts = adjacent.stream().map(v -> v.val).collect(Collectors.toList());
      List<Integer> otherAdjInts = vertex.adjacent.stream().map(v -> v.val).collect(Collectors.toList());

      boolean adjacentSame = adjacentInts.equals(otherAdjInts) && adjacentLevels.equals(otherAdjLevels);

      if (adjacentSame) {
        System.out.println(vertex);
        System.out.println(this);
        System.out.println();
      }

      return val == vertex.val && adjacentSame && level == vertex.level;
    }

    @Override
    public int hashCode() {
      return Objects.hash(val);
    }

    @Override
    public String toString() {
      StringBuilder s = new StringBuilder();

      for (Vertex v : adjacent) {
        s.append(v.val).append(" ");
      }

      return "Vertex{" + val + "[" + s + "]}";

    }
  }

  public static List<List<Vertex>> genGraph(List<String> lines) {
    List<List<Vertex>> made = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      String[] split = lines.get(i).split(" ");
      made.add(new ArrayList<>());

      for (int j = 0; j < split.length; j++) {
        Vertex v = new Vertex(Integer.parseInt(split[j]), i);
        int prevMin, prevMax;

        if (j == 0 || j == split.length -1) {
          prevMin = j == 0 ? 0 : split.length-2;
          prevMax = j == 0 ? 1 : split.length-1;
        } else {
          prevMin = j - 1;
          prevMax = j + 1;
        }

        if (i > 0) {
          for (Vertex prev : made.get(i-1).subList(prevMin, prevMax)) {
            prev.addTwoWayAdjacency(v);
          }
        }

        made.get(i).add(v);
      }
    }

    return made;
  }

  public static List<Vertex> dijkstras(List<List<Vertex>> graph, boolean findMaxCost) {
    return dijkstras(graph, graph.get(0).get(0), findMaxCost);
  }

  public static List<Vertex> dijkstras(List<List<Vertex>> graph, Vertex src, boolean findMaxCost) {
    List<Vertex> graphFlat = graph.stream().flatMap(List::stream).collect(Collectors.toList());

    HashMap<Vertex, Integer> dist = new HashMap<>();
    HashMap<Vertex, Vertex> prev = new HashMap<>();

    Comparator<Vertex> comparator = findMaxCost ? new MaxCompareDijkstra(dist) : new MinCompareDijkstra(dist);
    PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(comparator);
    List<Vertex> evaluated = new ArrayList<>();

    for (Vertex v : graphFlat) {
      dist.put(v, findMaxCost ? -100000 : 100000);
      prev.put(v, null);
    }

    dist.put(src, 0);
    priorityQueue.add(src);
    evaluated.add(src);

    while (!priorityQueue.isEmpty()) {
      Vertex u = priorityQueue.remove();

      for (Vertex v : u.adjacent.stream().filter(x -> x.level > u.level).collect(Collectors.toList())) {
          int alt = dist.get(u) + v.val;
          if (findMaxCost ? alt >= dist.get(v) : alt <= dist.get(v)) {
            dist.put(v, alt);
            prev.put(v, u);
            if (!evaluated.contains(v)) {
              priorityQueue.add(v);
              evaluated.add(v);
            }
        }
      }
    }

    List<Vertex> res = new ArrayList<>();
    Vertex last = graph.get(graph.size()-1).stream()
        .reduce((a, b) -> findMaxCost ?
            (dist.get(a) > dist.get(b) ? a : b) :
            (dist.get(a) < dist.get(b) ? a : b))
        .get();

    while (last != null) {
      res.add(0, last);
      last = prev.get(last);
    }

    System.out.println(res.stream().map(v -> v.val).collect(Collectors.toList()));

    return res;
  }

  private static record MinCompareDijkstra(HashMap<Vertex, Integer> dist) implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
      return dist.get(o1) - dist.get(o2);
    }
  }

  private static record MaxCompareDijkstra(HashMap<Vertex, Integer> dist) implements Comparator<Vertex> {
    @Override
    public int compare(Vertex o1, Vertex o2) {
      return dist.get(o2) - dist.get(o1);
    }
  }
}
