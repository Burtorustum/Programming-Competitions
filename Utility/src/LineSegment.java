import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineSegment {
  final Point2D p1;
  final Point2D p2;
  final SegmentType type;

  LineSegment(String line) {
    String[] s = line.split(" -> ");
    this.p1 = new Point2D(Arrays.stream(s[0].split(",")).map(Integer::valueOf).collect(Collectors.toList()));
    this.p2 = new Point2D(Arrays.stream(s[1].split(",")).map(Integer::valueOf).collect(Collectors.toList()));
    this.type = this.determineType();
  }

  LineSegment(Point2D p1, Point2D p2) {
    this.p1 = p1;
    this.p2 = p2;
    this.type = this.determineType();
  }


  private SegmentType determineType() {
    if (this.isVertical()) {
      return SegmentType.VERT;
    }
    if (this.isHorizontal()) {
      return SegmentType.HORIZ;
    }
    return SegmentType.DIAG;
  }

  boolean isHorzOrVert() {
    return this.type == SegmentType.HORIZ || this.type == SegmentType.VERT;
  }
  private boolean isHorizontal() {
    return p1.y == p2.y;
  }

  private boolean isVertical() {
    return p1.x == p2.x;
  }

  int xMin() {
    return Math.min(this.p1.x, this.p2.x);
  }

  int xMax() {
    return Math.max(this.p1.x, this.p2.x);
  }

  int yMin() {
    return Math.min(this.p1.y, this.p2.y);
  }

  int yMax() {
    return Math.max(this.p1.y, this.p2.y);
  }


  List<Point2D> pointsWithin() {
    List<Point2D> pts = new ArrayList<>();
    if (this.type == SegmentType.HORIZ) {
      for (int i = this.xMin(); i <= this.xMax(); i++) {
        pts.add(new Point2D(i, this.p1.y));
      }
    } else if (this.type == SegmentType.VERT){
      for (int i = this.yMin(); i <= this.yMax(); i++) {
        pts.add(new Point2D(this.p1.x, i));
      }
    } else {
      boolean subY = false;
      Point2D start;

      if (this.p1.x == xMin()) {
        start = p1;
      } else {
        start = p2;
      }

      if (start.y == this.yMax()) {
        subY = true;
      }

      int j = start.y;

      for (int i = start.x; i <= this.xMax(); i++) {
        pts.add(new Point2D(i, j));
        j += subY ? -1: 1;
      }
    }

    return pts;
  }


  @Override
  public String toString() {
    return "[" + this.p1 + "," + this.p2 + "]";
  }
}

enum SegmentType {
  HORIZ, VERT, DIAG
}
