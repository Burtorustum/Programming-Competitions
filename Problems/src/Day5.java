import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends AProblem {

  public Day5(String fileName) throws IOException {
    super(fileName);
  }

  @Override
  String solvePartOne() {
    List<LineSegment> segments = new ArrayList<>();

    for(String line : lines) {
      segments.add(new LineSegment(line));
    }

    segments = segments.stream().filter(LineSegment::isHorzOrVert).collect(Collectors.toList());
    List<Point2D> allPoints = segments.stream().map(LineSegment::pointsWithin)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    int count = 0;
    while (allPoints.size() > 0) {
      boolean first = true;

      Point2D cur = allPoints.remove(0);
      while (allPoints.contains(cur)) {
        if (first) {
          first = false;
          count++;
        }

        allPoints.remove(cur);
      }
    }

    return count + "";
  }

  @Override
  String solvePartTwo() {
    List<LineSegment> segments = new ArrayList<>();

    for(String line : lines) {
      segments.add(new LineSegment(line));
    }

    List<Point2D> allPoints = segments.stream().map(LineSegment::pointsWithin)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    int count = 0;
    while (allPoints.size() > 0) {
      boolean first = true;

      Point2D cur = allPoints.remove(0);
      if (allPoints.contains(cur)) {
        count++;

        allPoints.removeIf(cur::equals);
      }
    }

    return count + "";
  }

  @Override
  String name() {
    return "--- Day 5: Hydrothermal Venture ---";
  }
}
