import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day11 extends AProblem {

  public Day11(String fileName) throws IOException {
    super(fileName, "--- Day 11: Dumbo Octopus ---");
  }

  @Override
  String solvePartOne() {
    List<List<Integer>> grid = new ArrayList<>();
    genGrid(grid);

    AtomicInteger flashCount = new AtomicInteger(0);
    for (int i = 0; i < 100; i++) {
      grid = iterate(grid, flashCount);
    }

    return flashCount + "";
  }

  @Override
  String solvePartTwo() {
    List<List<Integer>> grid = new ArrayList<>();
    genGrid(grid);

    int i = 0;
    while (true) {
      if (grid.stream().allMatch(row -> row.stream().allMatch(n -> n == 0))) {
        return i + "";
      }

      grid = iterate(grid, new AtomicInteger());
      i++;
    }
  }

  void genGrid(List<List<Integer>> grid) {
    for (int row = 0; row < lines.size(); row++) {
      grid.add(new ArrayList<>());
      for (int i = 0 ; i < lines.get(row).length(); i++) {
        grid.get(row).add(Integer.parseInt(lines.get(row).substring(i,i+1)));
      }
    }
  }

  boolean canFlash(List<List<Integer>> grid) {
    return grid.stream().anyMatch(row->row.stream().anyMatch(n -> n > 9));
  }

  public List<List<Integer>> iterate(List<List<Integer>> grid, AtomicInteger numFlashes) {

    grid = grid.stream()
        .map(row -> row.stream().map(n -> n+1).collect(Collectors.toList()))
        .collect(Collectors.toList());

    while (canFlash(grid)) {
      for (int row = 0; row < grid.size(); row++) {
        for (int col = 0; col < grid.get(row).size(); col++) {
          if (grid.get(row).get(col) > 9) {
            numFlashes.incrementAndGet();
            grid.get(row).set(col, Integer.MIN_VALUE);
            ArrayPos cur = new ArrayPos(row, col);
            List<ArrayPos> adjacent = cur.getAdjacent(grid.size(), grid.get(row).size(), true, false);

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

    return grid;
  }
}
