import java.io.IOException;

public class Day17 extends AProblem {

  public Day17(String fileName) throws IOException {
    super(fileName, "--- Day 17: Trick Shot ---\n");
  }

  @Override
  String solvePartOne() {
    Pair<Point2D, Point2D> corners = getInput();
    int yMax = 0;

    for (int x = 0; x < 1000; x++) {
      for (int y = 0; y < 1000; y++) {
        Probe p = new Probe(x, y);
        while (!p.withinArea(corners) && p.couldEnterArea(corners) ) {
          p.step();
        }
        if (p.withinArea(corners)) {
          yMax = Math.max(yMax, p.yMax);
        }
      }
    }

    return "" + yMax;
  }

  @Override
  String solvePartTwo () {
    Pair<Point2D, Point2D> corners = getInput();
    int count = 0;

    for (int x = 0; x < 1000; x++) {
      for (int y = -1000; y < 1000; y++) {
        Probe p = new Probe(x, y);
        while (!p.withinArea(corners) && p.couldEnterArea(corners) ) {
          p.step();
        }
        if (p.withinArea(corners)) {
          count++;
        }
      }
    }

    return "" + count;
  }

  Pair<Point2D, Point2D> getInput () {
    String line = lines.get(0).split(":")[1];

    String[] x = line.strip().split(", ")[0].replace("x=", "").split("\\.\\.");
    String[] y = line.strip().split(", ")[1].replace("y=", "").split("\\.\\.");

    Point2D topLeftCorner = new Point2D(Integer.parseInt(x[0]), Integer.parseInt(y[1]));
    Point2D bottomRightCorner = new Point2D(Integer.parseInt(x[1]), Integer.parseInt(y[0]));

    return new Pair<>(topLeftCorner, bottomRightCorner);
  }

  static class Probe {
    int xPos, yPos;
    int xVel, yVel;
    int yMax;

    public Probe(int xVel, int yVel) {
      this(0, 0, xVel, yVel);
    }

    public Probe(int xPos, int yPos, int xVel, int yVel) {
      this.xPos = xPos;
      this.yPos = yPos;
      this.yMax = yPos;
      this.xVel = xVel;
      this.yVel = yVel;
    }

    public void step() {
      xPos += xVel;
      yPos += yVel;

      xVel = ((int) Math.signum(xVel)) * (Math.abs(xVel) - 1);
      yVel -= 1;

      yMax = Math.max(yPos, yMax);
    }

    public boolean withinArea(Pair<Point2D, Point2D> corners) {
      return this.xPos >= corners.one().x
          && this.xPos <= corners.two().x
          && this.yPos <= corners.one().y
          && this.yPos >= corners.two().y;
    }

    public boolean couldEnterArea(Pair<Point2D, Point2D> corners) {
      return (yPos >= corners.two().y)
          && ((xVel >= 0 && xPos <= corners.two().x) || (xVel < 0 && xPos >= corners.one().x));

    }
  }
}
