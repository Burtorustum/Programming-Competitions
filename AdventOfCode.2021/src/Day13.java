import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 extends AProblem {

  public Day13(String fileName) throws IOException {
    super(fileName, "--- Day 13: Transparent Origami ---");
  }

  @Override
  String solvePartOne() {
    Set<Point2D> points = getStartPos();
    List<String> folds = lines.stream().filter(s -> s.length() > 0 && s.charAt(0) == 'f').toList();

    String fold = folds.get(0);
    fold = fold.substring("fold along ".length());
    points = applyFold(points, fold);

    return "" + points.size();
  }

  @Override
  String solvePartTwo() {
    Set<Point2D> points = getStartPos();
    List<String> folds = lines.stream().filter(s -> s.length() > 0 && s.charAt(0) == 'f').toList();

    for (String f : folds) {
      f = f.substring("fold along ".length());
      points = applyFold(points, f);
    }

    return printPoints(points);
  }

  public Set<Point2D> getStartPos() {
    Set<Point2D> positionSet = new HashSet<>();

    int i = 0;
    while (!lines.get(i).equals("")) {
      String[] pos = lines.get(i).split(",");
      positionSet.add(new Point2D(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
      i++;
    }

    return positionSet;
  }

  public Set<Point2D> applyFold(Set<Point2D> points, String fold) {
    boolean foldUp = fold.charAt(0) == 'y';
    int val = Integer.parseInt(fold.substring(2));

    if (foldUp) {
      return points.stream().map(p -> p.y > val ? new Point2D(p.x, 2*val - p.y) : p).collect(Collectors.toSet());
    } else {
      return points.stream().map(p -> p.x > val ? new Point2D(2*val - p.x, p.y) : p).collect(Collectors.toSet());
    }
  }

  public String printPoints(Set<Point2D> points) {
    int maxX = points.stream().map(p -> p.x).max(Integer::compareTo).get();
    int maxY = points.stream().map(p -> p.y).max(Integer::compareTo).get();
    boolean[][] grid = new boolean[maxY+1][maxX+1];

    for (Point2D p : points) {
      grid[p.y][p.x] = true;
    }

    StringBuilder str = new StringBuilder();
    for (boolean[] booleans : grid) {
      str.append("\n");
      for (boolean b : booleans) {
        str.append(b ? "# " : " .");
      }
    }

    return str.toString().replaceAll("\\.", " ");
  }
}
