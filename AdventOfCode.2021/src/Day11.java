import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 extends AProblem {

  public Day11(String fileName) throws IOException {
    super(fileName, "--- Day 11: Dumbo Octopus ---\n");
  }

  @Override
  String solvePartOne() {
    List<List<Integer>> grid = new ArrayList<>();

    for (int row = 0; row < lines.size(); row++) {
      grid.add(new ArrayList<>());
      for (int i = 0 ; i < lines.get(row).length(); i++) {
        grid.get(row).add(Integer.parseInt(lines.get(row).substring(i,i+1)));
      }
    }

    int flashCount = 0;

    for (int i = 0; i < 100; i++) {
      grid = grid.stream()
          .map(row -> row.stream().map(n -> n+1).collect(Collectors.toList()))
          .collect(Collectors.toList());

      while (canFlash(grid)) {
        for (int row = 0; row < grid.size(); row++) {
          for (int col = 0; col < grid.get(row).size(); col++) {
            if (grid.get(row).get(col) > 9) {
              flashCount++;
              grid.get(row).set(col, Integer.MIN_VALUE);
              ArrayPos cur = new ArrayPos(row, col);
              List<ArrayPos> adjacent= new ArrayList<>();

              adjacent.add(getLeft(cur));
              adjacent.add(getRight(cur));
              adjacent.add(getDown(cur));
              adjacent.add(getUp(cur));
              adjacent.add(getLD(cur));
              adjacent.add(getLU(cur));
              adjacent.add(getRD(cur));
              adjacent.add(getRU(cur));

              for (ArrayPos p : adjacent) {
                if (p != null) {
                  int pInt = grid.get(p.row).get(p.col);
                  grid.get(p.row).set(p.col, pInt+1);
                }
              }
            }
          }
        }
      }

      grid  = grid.stream()
          .map(row -> row.stream().map(n -> n <= 0 ? 0 : n)
              .collect(Collectors.toList()))
          .collect(Collectors.toList());
    }

    return flashCount + "";
  }

  boolean canFlash(List<List<Integer>> grid) {
    return grid.stream().map(row->row.stream().anyMatch(n -> n > 9)).reduce((b1, b2) -> b1 || b2).get();
  }

  ArrayPos getLeft(ArrayPos in) {
    if (in.col != 0) {
      return new ArrayPos(in.row, in.col-1);
    }
    return null;
  }

  ArrayPos getRight(ArrayPos in) {
    if (in.col != lines.get(0).length()-1) {
      return new ArrayPos(in.row, in.col+1);
    }
    return null;
  }

  ArrayPos getUp(ArrayPos in) {
    if (in.row != 0) {
      return new ArrayPos(in.row-1, in.col);
    }
    return null;
  }

  ArrayPos getDown(ArrayPos in) {
    if (in.row != lines.size()-1) {
      return new ArrayPos(in.row+1, in.col);
    }
    return null;
  }

  ArrayPos getLU(ArrayPos in) {
    ArrayPos L = this.getLeft(in);
    ArrayPos U = this.getUp(in);
    if (L != null && U != null) {
      return new ArrayPos(U.row, L.col);
    }
    return null;
  }

  ArrayPos getLD(ArrayPos in) {
    ArrayPos L = this.getLeft(in);
    ArrayPos D = this.getDown(in);
    if (L != null && D != null) {
      return new ArrayPos(D.row, L.col);
    }
    return null;
  }

  ArrayPos getRU(ArrayPos in) {
    ArrayPos R = this.getRight(in);
    ArrayPos U = this.getUp(in);
    if (R != null && U != null) {
      return new ArrayPos(U.row, R.col);
    }
    return null;
  }

  ArrayPos getRD(ArrayPos in) {
    ArrayPos R = this.getRight(in);
    ArrayPos D = this.getDown(in);
    if (R != null && D != null) {
      return new ArrayPos(D.row, R.col);
    }
    return null;
  }

  @Override
  String solvePartTwo() {
    List<List<Integer>> grid = new ArrayList<>();

    for (int row = 0; row < lines.size(); row++) {
      grid.add(new ArrayList<>());
      for (int i = 0 ; i < lines.get(row).length(); i++) {
        grid.get(row).add(Integer.parseInt(lines.get(row).substring(i,i+1)));
      }
    }

    for (int i = 0; i < 1000; i++) {
      if (grid.stream().allMatch(row -> row.stream().allMatch(n -> n == 0))) {
        return i + "";
      }

      grid = grid.stream()
          .map(row -> row.stream().map(n -> n+1).collect(Collectors.toList()))
          .collect(Collectors.toList());

      while (canFlash(grid)) {
        for (int row = 0; row < grid.size(); row++) {
          for (int col = 0; col < grid.get(row).size(); col++) {
            if (grid.get(row).get(col) > 9) {
              grid.get(row).set(col, Integer.MIN_VALUE);
              ArrayPos cur = new ArrayPos(row, col);
              List<ArrayPos> adjacent= new ArrayList<>();

              adjacent.add(getLeft(cur));
              adjacent.add(getRight(cur));
              adjacent.add(getDown(cur));
              adjacent.add(getUp(cur));
              adjacent.add(getLD(cur));
              adjacent.add(getLU(cur));
              adjacent.add(getRD(cur));
              adjacent.add(getRU(cur));

              for (ArrayPos p : adjacent) {
                if (p != null) {
                  int pInt = grid.get(p.row).get(p.col);
                  grid.get(p.row).set(p.col, pInt+1);
                }
              }
            }
          }
        }
      }

      grid  = grid.stream()
          .map(row -> row.stream().map(n -> n <= 0 ? 0 : n)
              .collect(Collectors.toList()))
          .collect(Collectors.toList());
    }

    return "";
  }
}
